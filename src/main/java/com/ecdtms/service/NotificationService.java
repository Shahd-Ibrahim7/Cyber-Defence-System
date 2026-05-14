package com.ecdtms.service;

public class NotificationService {

    public void sendAlert(String message, String priority) {
        if (message == null || message.trim().isEmpty()) {
            return;
        }

        String cleanPriority = (priority != null) ? priority.toUpperCase().trim() : "INFO";
        System.out.println("[" + cleanPriority + "] Notification: " + message.trim());
    }

    public void notifyAdmin(String incidentTitle) {
        if (incidentTitle != null) {
            sendAlert("Critical Incident Detected: " + incidentTitle, "HIGH");
        }
    }
}