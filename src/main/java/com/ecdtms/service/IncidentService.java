package com.ecdtms.service;

import com.ecdtms.repository.IncidentRepository;
import com.ecdtms.repository.SecurityLogRepository;
import java.sql.SQLException;

public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final SecurityLogRepository securityLogRepository;

    public IncidentService() {
        this.incidentRepository = new IncidentRepository();
        this.securityLogRepository = new SecurityLogRepository();
    }

    public void createIncident(String title, String deviceId, String threatId, int userId, String status) throws SQLException {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Incident title cannot be empty");
        }

        incidentRepository.logIncident(title.trim(), deviceId, threatId, userId, status);
        securityLogRepository.addLog(userId, "CREATE_INCIDENT", "Incident created for device: " + deviceId);
    }

    public java.util.List<com.ecdtms.model.incident.Incident> getAllIncidents() throws SQLException {
        return incidentRepository.getAllIncidents();
    }

    public void updateIncident(int incidentId,
                                 String title,
                                 String deviceId,
                                 String threatId,
                                 String status,
                                 int userId) throws SQLException {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Incident title cannot be empty");
        }

        incidentRepository.updateIncident(incidentId, title.trim(), deviceId, threatId, status);
        securityLogRepository.addLog(userId, "UPDATE_INCIDENT", "Incident updated: " + title);
    }

    public void deleteIncident(int incidentId, int userId) throws SQLException {
        incidentRepository.deleteIncident(incidentId);
        securityLogRepository.addLog(userId, "DELETE_INCIDENT", "Incident deleted: #" + incidentId);
    }

    public void updateStatus(int incidentId, String status, int userId) throws SQLException {
        incidentRepository.updateIncidentStatus(incidentId, status);
        securityLogRepository.addLog(userId, "UPDATE_INCIDENT", "Status changed to: " + status);
    }
}

