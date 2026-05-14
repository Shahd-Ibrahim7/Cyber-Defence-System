package com.ecdtms.model.logging;

import java.time.LocalDateTime;

public class SecurityLog {

    private String logId;
    private String action;
    private String source;
    private LocalDateTime timestamp;

    public SecurityLog(String logId, String action) {
        this.logId = logId;
        this.action = action;
        this.source = "SYSTEM";
        this.timestamp = LocalDateTime.now();
    }

    public SecurityLog(String logId, String action, String source) {
        this.logId = logId;
        this.action = action;
        this.source = source;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] LOG-" + logId +
                " | Action: " + action +
                " | Source: " + source;
    }
}