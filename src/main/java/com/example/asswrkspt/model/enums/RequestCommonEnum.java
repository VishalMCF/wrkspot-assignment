package com.example.asswrkspt.model.enums;


public enum RequestCommonEnum {

    REQUEST_ID_HEADER("embibe-requestId"),
    PROCESSING_TIME_HEADER("x-processing-time"),
    INSTANCE_ID("embibe-instanceId"),
    CACHE_CONTROL_HEADER("Cache-Control"),
    STRICT_TRANSPORT_SECURITY_HEADER("strict-transport-security"),
    X_CONTENT_TYPE_OPTIONS_HEADER("X-Content-Type-Options"),
    X_XSS_PROTECTION_HEADER("X-XSS-Protection"),
    X_FRAME_OPTIONS_HEADER("X-Frame-Options"),
    SAME_ORIGIN("SAMEORIGIN"),
    MODE_BLOCK("1; mode=block"),
    NO_SNIFF("nosniff"),
    NO_CACHE("no-cache"),
    MAX_AGE_31536000_INCLUDE_SUBDOMAINS("maxage=31536000; include subdomains");

    private String name;

    private RequestCommonEnum(final String name) {
        this.name = name;
    }

    private RequestCommonEnum() {
    }

    public String getName() {
        return this.name;
    }

}
