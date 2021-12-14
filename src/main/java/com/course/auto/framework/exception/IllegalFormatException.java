package com.course.auto.framework.exception;

public class IllegalFormatException extends BaseException {
    public IllegalFormatException() {
    }

    public IllegalFormatException(String format, String... args) {
        super(format, args);
    }

    public IllegalFormatException(String message) {
        super(message);
    }

    public IllegalFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFormatException(Throwable cause) {
        super(cause);
    }

    public IllegalFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}