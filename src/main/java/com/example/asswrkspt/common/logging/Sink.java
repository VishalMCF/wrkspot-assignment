package com.example.asswrkspt.common.logging;

import java.util.Map;

import com.example.asswrkspt.model.event.LoggingEventBase;
import com.example.asswrkspt.web.exceptions.ErrorCode;

public interface Sink {
    void write(LoggingEventBase loggingEventBase);

    void info(String message, String... args);

    void info(String message, Map<String, Object> extra, String... args);

    void error(String message, ErrorCode errorCode, String... args);

    void error(String message, ErrorCode errorCode, Map<String, Object> extra, String... args);

    void error(String message, ErrorCode errorCode, Exception exception, String... args);

    void error(String message, ErrorCode errorCode, Exception exception, Map<String, Object> extra, String... args);
}
