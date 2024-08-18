package com.einstein.intelligence.exception;


import com.einstein.intelligence.entity.constant.BusinessEnum;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/8/1
 */
public class BusinessException extends BaseException {

    private final int code;

    private final String msg;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(BusinessEnum bizEnum) {
        super(bizEnum.getMessage());
        this.code = bizEnum.getCode();
        this.msg = bizEnum.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
