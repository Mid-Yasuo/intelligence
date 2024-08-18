package com.einstein.intelligence.exception;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
public abstract class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
