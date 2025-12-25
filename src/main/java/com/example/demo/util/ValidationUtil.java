package com.example.demo.util;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isTrue(Boolean value) {
        return value != null && value;
    }

    public static boolean isFalse(Boolean value) {
        return value == null || !value;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean notNull(Object obj) {
        return obj != null;
    }
}
