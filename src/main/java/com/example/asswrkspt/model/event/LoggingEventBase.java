package com.example.asswrkspt.model.event;

import java.util.Map;

import com.example.asswrkspt.common.http.RequestScope;
import com.example.asswrkspt.common.utils.CommonUtils;
import com.example.asswrkspt.config.ServiceConfiguration;
import com.example.asswrkspt.model.enums.LoggingType;

import lombok.Data;

@Data
public abstract class LoggingEventBase {

    private final String env;
    private final String type;
    private String requestId;
    private final String serviceName;
    private final String timestamp;
    private final String name;
    private final long threadId;
    private final String logEventType;
    private final Map<String, Object> extra;

    protected LoggingEventBase(String name, LoggingType type, LogEventType logEventType, Map<String, Object> extra) {
        this.name = name;
        this.type = type.getLogType();
        this.logEventType = logEventType.getLogEventType();
        this.timestamp = CommonUtils.getNowDateTimeInUTCIso();
        this.requestId = RequestScope.getRequestId();
        this.env = ServiceConfiguration.getProfile();
        this.serviceName = ServiceConfiguration.getApplicationName();
        this.requestId = RequestScope.getRequestId();
        this.threadId = Thread.currentThread().getId();
        this.extra = extra;
    }
}
