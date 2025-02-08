package com.chunjiez.business.service.mq;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/8
 */
public interface MqService<T> {

    void sendMessage(String topic, T msg);
}
