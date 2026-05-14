package com.ecdtms.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SecurityLogger {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String level, String action, String details) {
        String timestamp = LocalDateTime.now().format(formatter);

        System.out.printf("[%s] [%s] ACTION: %s | DETAILS: %s%n",
                timestamp,
                level.toUpperCase(),
                action,
                details);
    }

    public static void logInfo(String action, String details) {
        log("INFO", action, details);
    }

    public static void logWarning(String action, String details) {
        log("WARNING", action, details);
    }

    public static void logError(String action, String details) {
        log("ERROR", action, details);
    }
}