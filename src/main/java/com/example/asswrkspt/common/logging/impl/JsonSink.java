package com.example.asswrkspt.common.logging.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.asswrkspt.common.logging.Sink;
import com.example.asswrkspt.common.utils.JSONUtils;
import com.example.asswrkspt.model.enums.LoggingType;
import com.example.asswrkspt.model.event.ErrorLoggingEvent;
import com.example.asswrkspt.model.event.InfoLoggingEvent;
import com.example.asswrkspt.model.event.LoggingEventBase;
import com.example.asswrkspt.web.exceptions.ErrorCode;

@Service
public class JsonSink implements Sink {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSink.class.getName());

    public JsonSink() {
    }

    public void write(LoggingEventBase loggingEventBase) {
        if (LoggingType.INFO.getLogType().equalsIgnoreCase(loggingEventBase.getType())) {
            LOGGER.info(JSONUtils.toJson(loggingEventBase));
        } else if (LoggingType.WARN.getLogType().equalsIgnoreCase(loggingEventBase.getType())) {
            LOGGER.warn(JSONUtils.toJson(loggingEventBase));
        } else {
            LOGGER.error(JSONUtils.toJson(loggingEventBase));
        }

    }

    public void info(String message, String... args) {
        this.write(new InfoLoggingEvent(message, args));
    }

    public void info(String message, Map<String, Object> extra, String... args) {
        this.write(new InfoLoggingEvent(message, extra, args));
    }

    public void error(String message, ErrorCode errorCode, String... args) {
        this.write(new ErrorLoggingEvent(message, errorCode, args));
    }

    public void error(String message, ErrorCode errorCode, Map<String, Object> extra, String... args) {
        this.write(new ErrorLoggingEvent(message, errorCode, extra, args));
    }

    public void error(String message, ErrorCode errorCode, Exception exception, String... args) {
        this.write(new ErrorLoggingEvent(message, errorCode, exception, args));
    }

    public void error(String message, ErrorCode errorCode, Exception exception, Map<String, Object> extra, String... args) {
        this.write(new ErrorLoggingEvent(message, errorCode, exception, extra, args));
    }
}
