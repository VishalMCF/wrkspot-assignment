package com.example.asswrkspt.common.utils;

import com.example.asswrkspt.web.exceptions.ErrorCode;
import com.example.asswrkspt.web.exceptions.WrkSpotCommonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONUtils {
    private static ObjectMapper OBJECT_MAPPER = null;
    private static ObjectMapper PRETTY_PRINT_OBJECT_MAPPER = null;

    private static ObjectMapper getObjectMapper() {
        if (OBJECT_MAPPER == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            OBJECT_MAPPER = mapper;
        }

        return OBJECT_MAPPER;
    }

    private static ObjectMapper getPrettyPrintObjectMapper() {
        if (PRETTY_PRINT_OBJECT_MAPPER == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            PRETTY_PRINT_OBJECT_MAPPER = mapper;
        }

        return PRETTY_PRINT_OBJECT_MAPPER;
    }

    public static <T> String toJson(T data) {
        if (data == null) {
            return "";
        } else {
            try {
                return getObjectMapper().writeValueAsString(data);
            } catch (JsonProcessingException var2) {
                JsonProcessingException exception = var2;
                throw WrkSpotCommonException.getOrThrow(ErrorCode.getGenericError(), "Error while serializing the object", exception);
            }
        }
    }

    public static <T> String toJson(T data, boolean prettyPrint) {
        if (data == null) {
            return "";
        } else {
            try {
                return (prettyPrint ? getPrettyPrintObjectMapper() : getObjectMapper()).writeValueAsString(data);
            } catch (JsonProcessingException var3) {
                JsonProcessingException exception = var3;
                throw WrkSpotCommonException.getOrThrow(ErrorCode.getGenericError(), "Error while serializing the object", exception);
            }
        }
    }

    public static <T> T fromJsonToObject(String json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (Exception var3) {
            Exception e = var3;
            throw WrkSpotCommonException.getOrThrow(ErrorCode.getGenericError(), "Invalid Json", e);
        }
    }

    public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference) {
        try {
            return getObjectMapper().readValue(json, typeReference);
        } catch (Exception var3) {
            Exception e = var3;
            throw WrkSpotCommonException.getOrThrow(ErrorCode.getGenericError(), "Invalid Json", e);
        }
    }

    private JSONUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
