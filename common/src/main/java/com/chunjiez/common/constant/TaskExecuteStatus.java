package com.chunjiez.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Getter
public enum TaskExecuteStatus {
    /**
     * 任务状态:0-进行中 1-已完成 2-执行失败
     */
    IN_PROCESS(0),
    SUCCESS(1),
    ERROR(2),
    ;
    private final int status;

    TaskExecuteStatus(int status) {
        this.status = status;
    }
}
