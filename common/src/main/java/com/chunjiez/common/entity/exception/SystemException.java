package com.chunjiez.common.entity.exception;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/8/1
 */
public class SystemException extends BaseException {

    public SystemException(Throwable throwable) {
        super(throwable.getMessage());
    }

    public SystemException(String msg) {
        super(msg);
    }
}
