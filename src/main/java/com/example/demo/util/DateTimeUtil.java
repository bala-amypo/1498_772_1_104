package com.example.demo.util;

import java.time.LocalDateTime;

public final class DateTimeUtil {

    private DateTimeUtil() {}

    public static LocalDateTime nowIfNull(LocalDateTime value) {
        return value == null ? LocalDateTime.now() : value;
    }
}
