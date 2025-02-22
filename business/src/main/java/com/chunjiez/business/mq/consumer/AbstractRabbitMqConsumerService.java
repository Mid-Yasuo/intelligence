package com.chunjiez.business.mq.consumer;

import com.chunjiez.common.util.JsonUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
@Slf4j
public abstract class AbstractRabbitMqConsumerService<T> implements ConsumerService {

    @SuppressWarnings("unchecked")
    private final Class<T> clazz = (Class<T>) Object.class;

    /**
     * 消息
     */
    private final ThreadLocal<Message> messageThreadLocal = new ThreadLocal<>();

    /**
     * 通道
     */
    private final ThreadLocal<Channel> channelThreadLocal = new ThreadLocal<>();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        messageThreadLocal.set(message);
        channelThreadLocal.set(channel);
        onConsumer(genObject(new String(message.getBody())));
        messageThreadLocal.remove();
        channelThreadLocal.remove();
    }

    private T genObject(String text) throws Exception {
        try {
            return JsonUtils.toJavaBean(text, clazz);
        } catch (Exception exception) {
            log.error("message [{}] convert to been error.", text, exception);
        }
        Constructor<T> constructor = clazz.getConstructor(clazz);
        // 传递参数创建实例
        return constructor.newInstance(text);
    }

    public void onConsumer(T data) {
        log.warn("This method is not implemented: {}", data);
    }

    /**
     * 确认消息
     *
     * @param multiple 是否批量确认
     * @throws IOException
     */
    public void ack(boolean multiple) throws IOException {
        Message message = this.messageThreadLocal.get();
        channelThreadLocal.get().basicAck(message.getMessageProperties().getDeliveryTag(), multiple);
    }

    /**
     * 拒绝消息
     *
     * @param requeue 是否重新入队
     * @throws IOException
     */
    public void nack(boolean requeue) throws IOException {
        Message message = this.messageThreadLocal.get();
        channelThreadLocal.get().basicReject(message.getMessageProperties().getDeliveryTag(), requeue);
    }

    /**
     * 是否拒绝并重新放入队列
     *
     * @param multiple
     * @param requeue
     * @throws IOException
     */
    protected void nack(boolean multiple, boolean requeue) throws IOException {
        Message message = this.messageThreadLocal.get();
        this.channelThreadLocal.get().basicNack(message.getMessageProperties().getDeliveryTag(), multiple, requeue);
    }
}
