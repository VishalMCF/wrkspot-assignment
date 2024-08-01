package com.example.asswrkspt.model.event;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;
import java.util.Map;

import com.example.asswrkspt.model.enums.LoggingType;
import com.example.asswrkspt.web.exceptions.ErrorCode;

public class ErrorLoggingEvent extends LoggingEventBase {

    private final String message;
    private final String errorCode;
    private final boolean fatal;
    private final String errorType;

    public ErrorLoggingEvent(String message, ErrorCode errorCode, String... args) {
        super("error", LoggingType.ERROR, LogEventType.DIAGNOSTIC, (Map)null);
        this.errorCode = errorCode.getCode();
        this.errorType = errorCode.getErrorType().getErrorType();
        this.fatal = errorCode.getErrorType().isFatal();
        if (args.length > 0) {
            this.message = String.format(message, Arrays.stream(args).toArray());
        } else {
            this.message = message;
        }

    }

    public ErrorLoggingEvent(String message, ErrorCode errorCode, Map<String, Object> extra, String... args) {
        super("error", LoggingType.ERROR, LogEventType.DIAGNOSTIC, extra);
        this.errorCode = errorCode.getCode();
        this.errorType = errorCode.getErrorType().getErrorType();
        this.fatal = errorCode.getErrorType().isFatal();
        if (args.length > 0) {
            this.message = String.format(message, Arrays.stream(args).toArray());
        } else {
            this.message = message;
        }

    }

    public ErrorLoggingEvent(String message, ErrorCode errorCode, Exception exception, String... args) {
        super("error", LoggingType.ERROR, LogEventType.DIAGNOSTIC, (Map)null);
        this.errorCode = errorCode.getCode();
        this.errorType = errorCode.getErrorType().getErrorType();
        this.fatal = errorCode.getErrorType().isFatal();
        if (args.length > 0) {
            this.message = String.format("Message: %s, exception %s", String.format(message, Arrays.stream(args).toArray()), ExceptionUtils.getStackTrace(exception));
        } else {
            this.message = String.format("Message: %s, exception %s", message, ExceptionUtils.getStackTrace(exception));
        }

    }

    public ErrorLoggingEvent(String message, ErrorCode errorCode, Exception exception, Map<String, Object> extra, String... args) {
        super("error", LoggingType.ERROR, LogEventType.DIAGNOSTIC, extra);
        this.errorCode = errorCode.getCode();
        this.errorType = errorCode.getErrorType().getErrorType();
        this.fatal = errorCode.getErrorType().isFatal();
        if (args.length > 0) {
            this.message = String.format("Message: %s, exception %s", String.format(message, Arrays.stream(args).toArray()), ExceptionUtils.getStackTrace(exception));
        } else {
            this.message = String.format("Message: %s, exception %s", message, ExceptionUtils.getStackTrace(exception));
        }

    }

    public String getMessage() {
        return this.message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public boolean isFatal() {
        return this.fatal;
    }

    public String getErrorType() {
        return this.errorType;
    }

}
