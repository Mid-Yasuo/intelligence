package com.chunjiez.business.service.mq;

import org.springframework.stereotype.Service;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/8
 */
@Service
public class RabbitMqServiceImpl<T> implements MqService<T> {

    @Override
    public void sendMessage(String topic, T msg) {

    }
}
