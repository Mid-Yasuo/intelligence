package com.einstein.intelligence.entity.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Getter
public enum ActivityStatusEnum {
    /**
     * 活动状态：0-未开始 1-进行中 2-已结束 3-已取消
     */
    NOT_STARTED(0),
    STARTED(1),
    ENDED(2),
    CANCEL(3),
    ;
    private final int code;


    ActivityStatusEnum(int code) {
        this.code = code;
    }
}
