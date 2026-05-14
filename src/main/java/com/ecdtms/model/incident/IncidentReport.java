package com.ecdtms.model.incident;

import com.ecdtms.model.interfaces.Reportable;
import java.time.format.DateTimeFormatter;

public class IncidentReport implements Reportable {

    private Incident incident;
    private String resolutionSummary;
    private String severityLevel;

    public IncidentReport(Incident incident, String resolutionSummary) {
        this.incident = incident;
        this.resolutionSummary = resolutionSummary;

        this.severityLevel = (incident.getDetectedThreat() != null)
                ? incident.getDetectedThreat().getSeverity().toString()
                : "UNKNOWN";
    }

    @Override
    public void generateReport() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("========================================");
        System.out.println("OFFICIAL INCIDENT REPORT");
        System.out.println("Report ID: R-" + incident.getIncidentId());
        System.out.println("Date: " + incident.getTimestamp().format(formatter));
        System.out.println("Incident Title: " + incident.getTitle());
        System.out.println("Severity: " + severityLevel);

        System.out.println("Affected Asset: " +
                (incident.getAffectedDevice() != null
                        ? incident.getAffectedDevice().getIpAddress()
                        : "N/A"));

        System.out.println("Resolution: " + resolutionSummary);
        System.out.println("Current Status: " + incident.getStatus());
        System.out.println("========================================");
    }
}