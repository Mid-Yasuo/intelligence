package com.einstein.common.entity.exception.authentication;

import com.einstein.common.constant.BusinessEnum;
import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Getter
public class UnauthorizedException extends AuthenticationException {

    private final int httpStatus = 401;

    public UnauthorizedException(int code, String message) {
        super("UNKNOWN", code, message);
    }

    public UnauthorizedException(BusinessEnum businessEnum) {
        super("UNKNOWN", businessEnum.getCode(), businessEnum.getMessage());
    }
}
