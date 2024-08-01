package com.example.asswrkspt.common.http;

import org.apache.logging.log4j.util.Strings;

import java.util.function.Supplier;

import org.slf4j.MDC;

import com.example.asswrkspt.common.utils.RequestUtil;
import com.example.asswrkspt.model.enums.RequestCommonEnum;

public class RequestScope {

    public RequestScope() {
    }

    public static String getRequestId() {
        return getHeaderValue(RequestCommonEnum.REQUEST_ID_HEADER);
    }

    public static String getXFrameOptions() {
        return getHeaderValue(RequestCommonEnum.X_FRAME_OPTIONS_HEADER);
    }

    public static String getXContentTypeOptions() {
        return getHeaderValue(RequestCommonEnum.X_CONTENT_TYPE_OPTIONS_HEADER);
    }

    public static String getXXssProtectionX() {
        return getHeaderValue(RequestCommonEnum.X_XSS_PROTECTION_HEADER);
    }

    public static String getStrictTransportSecurity() {
        return getHeaderValue(RequestCommonEnum.STRICT_TRANSPORT_SECURITY_HEADER);
    }

    public static String getCacheControl() {
        return getHeaderValue(RequestCommonEnum.CACHE_CONTROL_HEADER);
    }

    public static void setRequestId(String requestId) {
        MDC.put(RequestCommonEnum.REQUEST_ID_HEADER.getName(), requestId);
    }

    public static void setXFrameOptions(String xFrameOptions) {
        MDC.put(RequestCommonEnum.X_FRAME_OPTIONS_HEADER.getName(), xFrameOptions);
    }

    public static void setXContentTypeOptions(String xContentTypeOptions) {
        MDC.put(RequestCommonEnum.X_CONTENT_TYPE_OPTIONS_HEADER.getName(), xContentTypeOptions);
    }

    public static void setXXssProtectionX(String xXssProtectionX) {
        MDC.put(RequestCommonEnum.X_XSS_PROTECTION_HEADER.getName(), xXssProtectionX);
    }

    public static void setStrictTransportSecurity(String strictTransportSecurity) {
        MDC.put(RequestCommonEnum.STRICT_TRANSPORT_SECURITY_HEADER.getName(), strictTransportSecurity);
    }

    public static void setCacheControl(String cacheControl) {
        MDC.put(RequestCommonEnum.CACHE_CONTROL_HEADER.getName(), cacheControl);
    }

    public static void clear() {
        MDC.clear();
    }

    private static String getHeaderValue(RequestCommonEnum header) {
        String headerValue = MDC.get(header.getName());
        if (Strings.isBlank(headerValue)) {
            headerValue = (String)((Supplier) RequestUtil.HEADER_MAP.get(header)).get();
            MDC.put(header.getName(), headerValue);
        }

        return headerValue;
    }

}
