package com.example.asswrkspt.model.event;

public enum LogEventType {
    DIAGNOSTIC("diagnostic"),
    METRIC("metric");

    private final String logEventType;

    private LogEventType(String logEventType) {
        this.logEventType = logEventType;
    }

    public String getLogEventType() {
        return this.logEventType;
    }
}
