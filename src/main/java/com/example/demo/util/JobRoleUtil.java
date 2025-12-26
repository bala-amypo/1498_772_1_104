package com.example.demo.util;

public final class JobRoleUtil {

    private JobRoleUtil() {}

    public static String defaultIfNull(String role) {
        return (role == null || role.trim().isEmpty())
                ? "STAFF"
                : role;
    }
}
