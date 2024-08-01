package com.example.asswrkspt.web.exceptions.enums;

import com.example.asswrkspt.web.exceptions.ErrorCode;
import com.example.asswrkspt.web.exceptions.ErrorType;

public enum CommonErrorCode implements ErrorCode {
    TIMEOUT_ERROR("timeout_error", CommonErrorType.FATAL),
    GENERIC_ERROR("generic_error", CommonErrorType.FATAL);

    private final String code;
    private final CommonErrorType errorType;

    private CommonErrorCode(String code, CommonErrorType errorType) {
        this.code = code;
        this.errorType = errorType;
    }

    public String getCode() {
        return this.code;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}
