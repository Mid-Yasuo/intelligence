package com.chunjiez.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/8
 */
public enum BooleanStatus {
    /**
     * Y-是 N-否
     */
    Y(1),
    N(0),;

    @Getter
    private final int status;

    BooleanStatus(int status) {
        this.status = status;
    }
}
