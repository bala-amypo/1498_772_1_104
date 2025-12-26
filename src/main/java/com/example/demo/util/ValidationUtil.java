package com.example.demo.util;

public final class ValidationUtil {

    private ValidationUtil() {}

    public static boolean isZeroOrNegative(Integer value) {
        return value == null || value <= 0;
    }
}
