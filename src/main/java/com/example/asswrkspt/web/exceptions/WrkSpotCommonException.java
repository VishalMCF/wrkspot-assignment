package com.example.asswrkspt.web.exceptions;

public class WrkSpotCommonException extends RuntimeException {
    private static final long serialVersionUID = 123456123131237L;
    private final ErrorCode errorCode;
    private final Object extra;

    public WrkSpotCommonException(Exception exception, ErrorCode errorCode) {
        super(exception);
        this.errorCode = errorCode;
        this.extra = null;
    }

    public WrkSpotCommonException(Exception exception) {
        super(exception);
        this.errorCode = ErrorCode.getGenericError();
        this.extra = null;
    }

    public WrkSpotCommonException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.extra = null;
    }

    public WrkSpotCommonException(ErrorCode errorCode, String message, Object extra) {
        super(message);
        this.errorCode = errorCode;
        this.extra = extra;
    }

    public WrkSpotCommonException(ErrorCode errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
        this.extra = null;
    }

    public static WrkSpotCommonException getOrThrow(ErrorCode errorCode, String message, Exception exception) {
        if (WrkSpotCommonException.class.isAssignableFrom(exception.getClass())) {
            throw (WrkSpotCommonException)exception;
        } else {
            throw new WrkSpotCommonException(errorCode, message, exception);
        }
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public Object getExtra() {
        return this.extra;
    }

    public String toString() {
        String var10000 = String.valueOf(this.getErrorCode());
        return "EmbibeCommonException(errorCode=" + var10000 + ", extra=" + String.valueOf(this.getExtra()) + ")";
    }
}
