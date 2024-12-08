package com.einstein.common.constant;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
public enum TaskStatusEnum {

    /**
     * 任务状态枚举
     */
    NORMAL(1, "normal"),
    RUNNING(2, "running");

    @Getter
    private final Integer status;

    @Getter
    private final String description;

    TaskStatusEnum(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public static String getReadableStatus(Integer status) {
        if (status == null) {
            return "unknown";
        }
        TaskStatusEnum t = Arrays.stream(values()).filter(s -> s.getStatus().equals(status)).findFirst().orElse(null);
        if (t == null) {
            return "unknown";
        }
        return t.description;
    }
}
