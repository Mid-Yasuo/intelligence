package com.einstein.intelligence.exception.authentication;

import com.einstein.intelligence.entity.constant.BusinessEnum;
import com.einstein.intelligence.exception.BaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthenticationException extends BaseException {

    private int code;

    private String username;

    private String message;

    public AuthenticationException(String username, int code, String message) {
        super(message);
        this.username = username;
        this.code = code;
        this.message = message;
    }

    public AuthenticationException(String username, BusinessEnum bizEnum) {
        super(bizEnum.getMessage());
        this.username = username;
        this.code = bizEnum.getCode();
        this.message = bizEnum.getMessage();
    }
}
