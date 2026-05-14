package com.ecdtms.model.logging;

import com.ecdtms.model.incident.Incident;

public class Alert {

    private String alertId;
    private String message;
    private Incident relatedIncident;

    public Alert(String alertId, String message, Incident incident) {
        this.alertId = alertId;
        this.message = message;
        this.relatedIncident = incident;
    }

    public void triggerAlert() {
        System.out.println("🚨 ALERT [" + alertId + "]: " + message);

        if (relatedIncident != null) {
            System.out.println("Related Incident: " + relatedIncident.getTitle());
        }
    }
}