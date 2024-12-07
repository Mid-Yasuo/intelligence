package com.einstein.common.constant;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/7/28
 */
public enum UserStatus {
    /**
     * 用户状态
     */
    UNACTIVATED(0, "未激活"),
    NORMAL(1, "正常"),
    LOCK(2, "锁定"),
    FORBIDDEN(3, "禁用"),
    LOGOUT(4, "注销"),
    ;
    int code;

    String description;

    UserStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
