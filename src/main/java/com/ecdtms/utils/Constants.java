package com.ecdtms.utils;

public final class Constants {

    // Application Info
    public static final String APP_TITLE = "ECDTMS - Cyber Defense System";

    // Database Tables (match MySQL exactly)
    public static final String TABLE_USERS = "users";
    public static final String TABLE_INCIDENTS = "incidents";
    public static final String TABLE_LOGS = "system_logs";

    // Severity Levels
    public static final String SEVERITY_CRITICAL = "CRITICAL";
    public static final String SEVERITY_HIGH = "HIGH";
    public static final String SEVERITY_MEDIUM = "MEDIUM";
    public static final String SEVERITY_LOW = "LOW";

    // Incident Status
    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_RESOLVED = "RESOLVED";
    public static final String STATUS_CLOSED = "CLOSED";

    private Constants() {
        // منع إنشاء object
    }
}