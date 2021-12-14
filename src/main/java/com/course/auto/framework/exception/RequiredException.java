package com.course.auto.framework.exception;

public class RequiredException extends BaseException {
    public RequiredException() {
    }

    public RequiredException(String format, String... args) {
        super(format, args);
    }

    public RequiredException(String message) {
        super(message);
    }

    public RequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredException(Throwable cause) {
        super(cause);
    }

    public RequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}