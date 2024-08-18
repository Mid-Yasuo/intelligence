package com.einstein.intelligence.entity.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Getter
public enum TaskStatusEnum {
    /**
     * 任务状态:0-进行中 1-已完成 2-执行失败
     */
    STARTED(0),
    SUCCESS(1),
    ERROR(2),
    ;
    private final int code;


    TaskStatusEnum(int code) {
        this.code = code;
    }
}
