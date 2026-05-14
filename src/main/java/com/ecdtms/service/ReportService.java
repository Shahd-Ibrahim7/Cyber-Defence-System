package com.ecdtms.service;

import java.sql.SQLException;

public class ReportService {

    public void generateIncidentReport(int incidentId) {
        if (incidentId <= 0) return;
        System.out.println("Generating PDF Report for Incident ID: " + incidentId);
    }

    public void exportSystemAuditLogs() throws SQLException {
        System.out.println("Exporting Security Logs to CSV format...");
    }
}