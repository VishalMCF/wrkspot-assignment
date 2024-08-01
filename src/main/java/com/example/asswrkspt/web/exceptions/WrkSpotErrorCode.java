package com.example.asswrkspt.web.exceptions;

import org.springframework.http.HttpStatus;

import com.example.asswrkspt.web.exceptions.enums.CommonErrorType;

import lombok.Getter;

public enum WrkSpotErrorCode implements ErrorCode {
    READ_ONLY_DATABASE_ERROR("read_only_database", HttpStatus.INTERNAL_SERVER_ERROR, CommonErrorType.FATAL),
    QUERY_NOT_FOUND("query_not_found", HttpStatus.NOT_FOUND, CommonErrorType.FATAL),
    INVALID_REQUEST("invalid_request", HttpStatus.BAD_REQUEST, CommonErrorType.BUSINESS),
    METHOD_NOT_ALLOWED("method_not_allowed", HttpStatus.METHOD_NOT_ALLOWED, CommonErrorType.BUSINESS),
    TIMEOUT_OCCURRED("timeout_occurred", HttpStatus.INTERNAL_SERVER_ERROR, CommonErrorType.FATAL),
    DATABASE_ERROR("database_error", HttpStatus.INTERNAL_SERVER_ERROR, CommonErrorType.FATAL),
    GENERIC_ERROR("generic_error", HttpStatus.INTERNAL_SERVER_ERROR, CommonErrorType.FATAL);

    @Getter
    private final HttpStatus httpStatus;
    private final String code;
    private final ErrorType errorType;

    WrkSpotErrorCode(String code,
        HttpStatus httpStatus,
        ErrorType errorType) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.errorType = errorType;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }
}
