package com.example.asswrkspt.model.enums;

public enum LoggingType {

    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private final String logType;

    private LoggingType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return this.logType;
    }
}
