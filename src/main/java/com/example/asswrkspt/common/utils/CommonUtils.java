package com.example.asswrkspt.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CommonUtils {

    public static String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    public static LocalDateTime getNowInUTC() {
        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);
        return nowUTC.toLocalDateTime();
    }

    public static long getNowInUTCMillis() {
        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);
        return nowUTC.toInstant().toEpochMilli();
    }

    public static String getDateTimeInUTCIso(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneOffset.UTC).toString();
    }

    public static String getNowDateTimeInUTCIso() {
        return getNowInUTC().toString();
    }

    private CommonUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
