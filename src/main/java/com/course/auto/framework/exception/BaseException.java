package com.course.auto.framework.exception;

import java.util.Formatter;

public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String format, Object... args) {
        super(new Formatter().format(format, args).toString());
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}