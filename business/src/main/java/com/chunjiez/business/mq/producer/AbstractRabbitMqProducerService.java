package com.chunjiez.business.mq.producer;

import cn.hutool.core.util.IdUtil;
import com.chunjiez.common.constant.RabbitMQEnum;
import com.chunjiez.common.util.CFunction;
import com.chunjiez.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
@Slf4j
public abstract class AbstractRabbitMqProducerService implements ProducerService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    private final String exchange;

    private final String routingKey;

    protected AbstractRabbitMqProducerService(String exchange, String routingKey) {
        this.exchange = String.format(RabbitMQEnum.EXCHANGE.getValue(), exchange);
        this.routingKey = String.format(RabbitMQEnum.ROUTER_KEY.getValue(),
                "default_routing_key");
    }

    @Override
    public boolean autoAck() {
        return false;
    }

    @Override
    public void send(Object message) {
        MessagePostProcessor messagePostProcessor = getMessagePostProcessor();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentEncoding("UTF-8");
        messageProperties.setContentType("text/plain");
        String data = JsonUtils.toJson(message);
        Message msg = new Message(data.getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.convertAndSend(this.exchange, this.routingKey, msg, messagePostProcessor);
    }

    @Override
    public void send(Object message, CFunction success, CFunction fail) {
        try {
            this.send(message);
            success.apply();
        } catch (Exception exception) {
            log.error("send message [{}] error.", message, exception);
        }
    }

    @Override
    public void delaySend(Object msg, Integer delay) {
        MessagePostProcessor messagePostProcessor = getMessagePostProcessor();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentEncoding("UTF-8");
        messageProperties.setContentType("text/plain");
        messageProperties.setDelay(delay);
        String data = JsonUtils.toJson(msg);
        Message message = new Message(data.getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.convertAndSend(this.exchange, this.routingKey, message, messagePostProcessor);
    }

    private MessagePostProcessor getMessagePostProcessor() {
        return message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setMessageId(IdUtil.randomUUID());
            messageProperties.setTimestamp(new Date());
            return message;
        };
    }
}
