package com.einstein.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Getter
public enum TaskExecuteStatusEnum {
    /**
     * 任务状态:0-进行中 1-已完成 2-执行失败
     */
    IN_PROCESS(0),
    SUCCESS(1),
    ERROR(2),
    ;
    private final int code;

    TaskExecuteStatusEnum(int code) {
        this.code = code;
    }
}
