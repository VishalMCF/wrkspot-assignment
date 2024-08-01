package com.example.asswrkspt.common.utils;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import com.example.asswrkspt.model.enums.RequestCommonEnum;

public class RequestUtil {

    public static final Map<RequestCommonEnum, Supplier<String>> HEADER_MAP;

    private RequestUtil() {
    }

    public static String getRequestId(HttpServletRequest request) {
        return getHeaderValue(RequestCommonEnum.REQUEST_ID_HEADER, request);
    }

    public static String getXFrameOptions(HttpServletRequest request) {
        return getHeaderValue(RequestCommonEnum.X_FRAME_OPTIONS_HEADER, request);
    }

    public static String getXXssProtectionX(HttpServletRequest request) {
        return getHeaderValue(RequestCommonEnum.X_XSS_PROTECTION_HEADER, request);
    }

    public static String getXContentTypeOptions(HttpServletRequest request) {
        return getHeaderValue(RequestCommonEnum.X_CONTENT_TYPE_OPTIONS_HEADER, request);
    }

    public static String getStrictTransportSecurity(HttpServletRequest request) {
        return getHeaderValue(RequestCommonEnum.STRICT_TRANSPORT_SECURITY_HEADER, request);
    }

    public static String getCacheControl(HttpServletRequest request) {
        return getHeaderValue(RequestCommonEnum.CACHE_CONTROL_HEADER, request);
    }

    private static String getHeaderValue(RequestCommonEnum header, HttpServletRequest request) {
        String headerValue = request.getHeader(header.getName());
        return StringUtils.isBlank(headerValue) ? (String)((Supplier)HEADER_MAP.get(header)).get() : headerValue;
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headers = new HashMap();
        if (headerNames != null) {
            while(headerNames.hasMoreElements()) {
                String header = (String)headerNames.nextElement();
                headers.put(header, request.getHeader(header));
            }
        }

        return headers;
    }

    static {
        FluentMap<RequestCommonEnum, Supplier<String>> map = new FluentMap();
        FluentMap var10000 = map.with(RequestCommonEnum.REQUEST_ID_HEADER, CommonUtils::generateUniqueID);
        RequestCommonEnum var10001 = RequestCommonEnum.X_FRAME_OPTIONS_HEADER;
        RequestCommonEnum var10002 = RequestCommonEnum.SAME_ORIGIN;
        Objects.requireNonNull(var10002);
        var10000 = var10000.with(var10001, var10002.getName());
        var10001 = RequestCommonEnum.X_XSS_PROTECTION_HEADER;
        var10002 = RequestCommonEnum.MODE_BLOCK;
        Objects.requireNonNull(var10002);
        var10000 = var10000.with(var10001, var10002.getName());
        var10001 = RequestCommonEnum.STRICT_TRANSPORT_SECURITY_HEADER;
        var10002 = RequestCommonEnum.MAX_AGE_31536000_INCLUDE_SUBDOMAINS;
        Objects.requireNonNull(var10002);
        var10000 = var10000.with(var10001, var10002.getName());
        var10001 = RequestCommonEnum.X_CONTENT_TYPE_OPTIONS_HEADER;
        var10002 = RequestCommonEnum.NO_SNIFF;
        Objects.requireNonNull(var10002);
        var10000 = var10000.with(var10001, var10002.getName());
        var10001 = RequestCommonEnum.CACHE_CONTROL_HEADER;
        var10002 = RequestCommonEnum.NO_CACHE;
        Objects.requireNonNull(var10002);
        var10000.with(var10001, var10002.getName());
        map.with(RequestCommonEnum.REQUEST_ID_HEADER, CommonUtils::generateUniqueID);
        HEADER_MAP = Collections.unmodifiableMap(map);
    }

}
