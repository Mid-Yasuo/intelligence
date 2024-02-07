package com.einstein.albert.intelligence.entity.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Getter
public enum UserWalletStatus {
    /**
     * 1-可用；2-冻结；3-销户
     */
    NORMAL(1),
    frozen(2),
    CANCEL(3);

    private final int value;

    UserWalletStatus(int value) {
        this.value = value;
    }
}
