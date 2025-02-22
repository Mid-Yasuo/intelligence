package com.chunjiez.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
@Getter
public enum RabbitMQEnum {

    /**
     * RabbitMQ 队列枚举
     */
    QUEUE("intelligence.%s.queue", "队列名称"),

    EXCHANGE("intelligence.%s.exchange", "交换机名称"),

    ROUTER_KEY("intelligence.%s.key", "路由名称");
    private final String value;

    private final String desc;

    RabbitMQEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
