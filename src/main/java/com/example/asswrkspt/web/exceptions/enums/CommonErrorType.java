package com.example.asswrkspt.web.exceptions.enums;

import com.example.asswrkspt.web.exceptions.ErrorType;

public enum CommonErrorType implements ErrorType {
    BUSINESS("business", false),
    FATAL("fatal", true);

    private final String errorType;
    private final boolean fatal;

    private CommonErrorType(String errorType, boolean fatal) {
        this.errorType = errorType;
        this.fatal = fatal;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public boolean isFatal() {
        return this.fatal;
    }
}
