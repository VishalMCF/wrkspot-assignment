package com.example.asswrkspt.model.event;

import java.util.Arrays;
import java.util.Map;

import com.example.asswrkspt.model.enums.LoggingType;

public class InfoLoggingEvent extends LoggingEventBase {
    private final String message;

    public InfoLoggingEvent(String message, String... args) {
        super("info", LoggingType.INFO, LogEventType.DIAGNOSTIC, (Map)null);
        if (args.length > 0) {
            this.message = String.format(message, Arrays.stream(args).toArray());
        } else {
            this.message = message;
        }

    }

    public InfoLoggingEvent(String message, Map<String, Object> extra, String... args) {
        super("info", LoggingType.INFO, LogEventType.DIAGNOSTIC, extra);
        if (args.length > 0) {
            this.message = String.format(message, Arrays.stream(args).toArray());
        } else {
            this.message = message;
        }

    }

    public String getMessage() {
        return this.message;
    }
}
