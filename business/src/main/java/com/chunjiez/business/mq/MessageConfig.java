package com.chunjiez.business.mq;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.chunjiez.business.mq.consumer.ConsumerService;
import com.chunjiez.business.mq.producer.ProducerService;
import com.chunjiez.common.constant.RabbitExchangeEnum;
import com.chunjiez.common.constant.RabbitMQEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
@Slf4j
@Component
public class MessageConfig implements SmartInitializingSingleton {

    private final ApplicationContext applicationContext;
    /**
     * MQ链接工厂
     */
    private final ConnectionFactory connectionFactory;

    /**
     * MQ操作管理器
     */
    private final AmqpAdmin amqpAdmin;


    public MessageConfig(ApplicationContext applicationContext, ConnectionFactory connectionFactory, AmqpAdmin amqpAdmin) {
        this.applicationContext = applicationContext;
        this.connectionFactory = configureConnectionFactory(connectionFactory);
        this.amqpAdmin = amqpAdmin;
    }


    private ConnectionFactory configureConnectionFactory(ConnectionFactory connectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = (CachingConnectionFactory) connectionFactory;
        // 设置连接超时时间
        cachingConnectionFactory.setConnectionTimeout(60000);
        // 启用连接自动恢复
        cachingConnectionFactory.getRabbitConnectionFactory().setAutomaticRecoveryEnabled(true);
        // 设置连接恢复时间间隔
        cachingConnectionFactory.getRabbitConnectionFactory().setNetworkRecoveryInterval(5000L);
        cachingConnectionFactory.getRabbitConnectionFactory().setTopologyRecoveryEnabled(true);
        return cachingConnectionFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, ProducerService> producerServiceMap = applicationContext.getBeansOfType(ProducerService.class)
                .values().stream().collect(Collectors.toMap(ProducerService::business, Function.identity()));
        Map<String, ConsumerService> consumerServiceMap = applicationContext.getBeansOfType(ConsumerService.class)
                .values().stream().collect(Collectors.toMap(ConsumerService::business, Function.identity()));
        producerServiceMap.forEach((k, p) -> {
            ConsumerService consumerService = consumerServiceMap.get(k);
            if (Objects.nonNull(consumerService)) {
                String queueName = String.format(RabbitMQEnum.QUEUE.getValue(), p.getExchangeName());
                Queue queue = getQueue(queueName, p.getArguments(), p.getDeadLetterExchange(), p.getDeadLetterRoutingKey());
                String exchangeName = String.format(RabbitMQEnum.EXCHANGE.getValue(), p.getExchangeName());
                Exchange exchange = getExchangeByType(p.getExchangeType(), exchangeName, p.getArguments());
                String routingKey = String.format(RabbitMQEnum.ROUTER_KEY.getValue(), p.getRoutingKey());
                queueBindExchange(queueName, exchangeName, routingKey, queue, exchange);
                try {
                    listenerContainerStart(consumerService, queue);
                    log.info("bing consumer: {}", consumerService.business());
                } catch (Exception exception) {
                    log.error("dont create consumer [{}]，you must extends AbsConsumerService.", consumerService.business(), exception);
                }
            }
        });
    }

    private Queue getQueue(String queueName, Map<String, Object> arguments, String deadLetterExchange, String deadLetterRoutingKey) {
        log.debug("init queue: {}", queueName);
        if (MapUtil.isEmpty(arguments)) {
            arguments = new HashMap<>();
        }
        // 绑定死信队列
        if (CharSequenceUtil.isNotBlank(deadLetterExchange) && CharSequenceUtil.isNotBlank(deadLetterRoutingKey)) {
            deadLetterExchange = String.format(RabbitMQEnum.EXCHANGE.getValue(), deadLetterExchange);
            deadLetterRoutingKey = String.format(RabbitMQEnum.ROUTER_KEY.getValue(), deadLetterRoutingKey);
            arguments.put("x-dead-letter-exchange", deadLetterExchange);
            arguments.put("x-dead-letter-routing-key", deadLetterRoutingKey);
            log.debug("binding dead exchange : exchange: {}, router: {}", deadLetterExchange, deadLetterRoutingKey);
        }

        return new Queue(queueName, true, false, false, arguments);
    }


    /**
     * 根据类型生成交换机
     *
     * @param exchangeType
     * @param exchangeName
     * @param arguments
     * @return
     */
    private Exchange getExchangeByType(RabbitExchangeEnum exchangeType, String exchangeName, Map<String, Object> arguments) {
        AbstractExchange exchange = null;
        switch (exchangeType) {
            // 直连交换机
            case DIRECT:
                exchange = new DirectExchange(exchangeName, true, false, arguments);
                break;
            // 主题交换机
            case TOPIC:
                exchange = new TopicExchange(exchangeName, true, false, arguments);
                break;
            //扇形交换机
            case FANOUT:
                exchange = new FanoutExchange(exchangeName, true, false, arguments);
                break;
            // 头交换机
            case HEADERS:
                exchange = new HeadersExchange(exchangeName, true, false, arguments);
                break;
            case DELAYED:
                exchange = new CustomExchange(exchangeName, "x-delayed-message", true, false, arguments);
                break;
            default:
                log.warn("not equal exchange type");
                break;
        }
        return exchange;
    }

    private void queueBindExchange(String queueName, String exchangeName, String routingKey, Queue queue, Exchange exchange) {
        log.debug("init queue exchange: {}", queueName);
        ;
        Binding binding = new Binding(queueName,
                Binding.DestinationType.QUEUE,
                exchangeName,
                routingKey,
                null);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
        log.info("queue banding exchange : queue: {}, exchange: {}, routingKey:{}", queueName, exchangeName, routingKey);
    }


    public void listenerContainerStart(ConsumerService consumerService, Queue queue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setAmqpAdmin(amqpAdmin);
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue);
        container.setPrefetchCount(20);
        container.setConcurrentConsumers(20);
        container.setMaxConcurrentConsumers(100);
        container.setDefaultRequeueRejected(Boolean.FALSE);
        container.setAcknowledgeMode(Boolean.TRUE.equals(consumerService.autoAck()) ? AcknowledgeMode.AUTO : AcknowledgeMode.MANUAL);
        container.setMessageListener(consumerService);
        container.start();
    }
}
