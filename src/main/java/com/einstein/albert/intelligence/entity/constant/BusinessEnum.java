package com.einstein.albert.intelligence.entity.constant;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/7/27
 */
public enum BusinessEnum {
    /**
     * 业务错误枚举列表
     * 101xxx 用户
     * 102xxx 角色
     * 103xxx 权限
     * 201xxx 活动
     * 202xxx 活动奖品
     */
    USER_NOT_FOUND(101104, "用户不存在"),
    USER_NOT_LOGIN(101105, "用户未登录"),
    USER_PASSWORD_ERROR(101106, "账户或密码错误"),
    USER_TOKEN_EXPIRE(101107, "用户令牌失效"),
    USER_TOKEN_EMPTY(101109, "用户令牌[Authorization]缺失"),
    USER_TOKEN_INVALID(101108, "用户令牌非法"),
    USER_LOGIN_ERROR(101108, "用户登录失败，请稍后重试"),

    USER_UNACTIVATED_ERROR(101201, "账户未激活"),
    USER_LOCKED_ERROR(101202, "账户已被锁定"),
    USER_FORBIDDEN_ERROR(101203, "账户已被禁用"),
    USER_LOG_OFF(101204, "账户已注销"),

    USER_WALLET_INSUFFICIENT_ERROR(101301, "用户钱包余额不足"),
    USER_WALLET_DEDUCTION_ERROR(101302, "用户钱包扣款失败"),
    USER_ACCESS_DENY(101403, "用户禁止访问"),

    ACTIVITY_NOT_FOUND(201404, "活动不存在"),
    ACTIVITY_HAS_PUBLISHED(201405, "活动已发布，无需重复发布"),
    ACTIVITY_NOT_STARTED(101408, "活动未开始"),
    ACTIVITY_SN_EMPTY(201409, "缺少活动编号"),
    ACTIVITY_UNPUBLISHED(201410, "活动未发布"),
    ACTIVITY_ENDED(201411, "活动已结束"),
    ACTIVITY_ERROR(201412, "活动异常"),
    ACTIVITY_WITHOUT_LOTTERY_CHANCE(201413, "活动抽奖已达到最大次数"),
    ACTIVITY_AWARD_NOT_FOUND(201414, "活动奖品不存在"),
    ACTIVITY_NOT_ENDED(201415, "活动未结束"),
    ACTIVITY_RUN_LOTTERY_IN_PROCESS(201416, "活动正在开奖中"),
    ACTIVITY_NOT_NEED_RUN_LOTTERY(201408, "活动无需开奖");

    private final int code;
    private final String message;

    BusinessEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
