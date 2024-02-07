package com.einstein.albert.intelligence.entity.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Getter
public enum AwardKindEnum {

    /**
     * 奖品类型 1-现金 2-积分
     */
    Cash(1),
    Integral(2);

    private final int kind;

    AwardKindEnum(int kind) {
        this.kind = kind;
    }
}
