package com.example.asswrkspt.web.exceptions;

import com.example.asswrkspt.web.exceptions.enums.CommonErrorCode;

public interface ErrorCode {
    String getCode();

    ErrorType getErrorType();

    static ErrorCode getGenericError() {
        return CommonErrorCode.GENERIC_ERROR;
    }
}
