package com.chunjiez.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/8
 */
@Getter
public enum NotificationChannel {

    /**
     * 通知渠道
     */
    System("System"),

    Email("Email"),

    SMS("SMS"),

    DingTalk("DingTalk"),

    Phone("Phone"),
    ;
    private final String channel;

    NotificationChannel(String channel) {
        this.channel = channel;
    }
}
