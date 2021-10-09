package com.example.common.exception;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
public class BusinessRuntimeException extends RuntimeException {
    public BusinessRuntimeException() {
        super();
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    protected BusinessRuntimeException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
