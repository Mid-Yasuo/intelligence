package com.einstein.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/7/27
 */
@Getter
public enum BusinessEnum {
    /**
     * 业务错误枚举列表
     * 101xxx 用户
     * 102xxx 角色
     * 103xxx 权限
     * <p>
     * 201xxx 任务
     * 202xxx 任务步骤
     * 203xxx 任务脚本
     * 204xxx 任务日志
     */
    USER_NOT_FOUND(101104, "用户不存在", "User not exist"),
    USER_NOT_LOGIN(101105, "用户未登录", "User not login"),
    USER_PASSWORD_ERROR(101106, "账户或密码错误", "Username or password error"),
    USER_TOKEN_EXPIRE(101107, "用户令牌失效", "User token expired"),
    USER_TOKEN_EMPTY(101109, "用户令牌[Authorization]必需", "User token [Authorization] is required"),
    USER_TOKEN_INVALID(101108, "用户令牌非法", "User token unavailable"),

    USER_UNACTIVATED_ERROR(101201, "账户未激活", "User is unactivated"),
    USER_LOCKED_ERROR(101202, "账户已被锁定", "User has been locked"),
    USER_FORBIDDEN_ERROR(101203, "账户已被禁用", "The User has been disabled"),
    USER_CANCEL_ACCOUNT_ERROR(101204, "账户已注销", "The user has been canceled"),

    TASK_NOT_EXIST(201404, "任务不存在", "Task not exist"),
    TASK_IN_PROCESS(201600, "任务正在执行中", "Task is in process"),
    TASK_NAME_DUPLICATE(201601, "任务名称重复", "Duplicate task name"),
    TASK_STEP_NOT_EXIST(202404, "任务步骤不存在", "Task step not exist"),
    TASK_STEP_NAME_DUPLICATE(202601, "任务步骤名称重复", "Duplicate task step name"),
    ;

    private final int code;
    private final String message;
    private final String enMessage;

    BusinessEnum(int code, String message, String enMessage) {
        this.code = code;
        this.message = message;
        this.enMessage = enMessage;
    }

}
