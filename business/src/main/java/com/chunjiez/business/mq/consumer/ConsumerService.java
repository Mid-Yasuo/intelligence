package com.chunjiez.business.mq.consumer;

import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
public interface ConsumerService extends ChannelAwareMessageListener {

    String business();

    boolean autoAck();

}
