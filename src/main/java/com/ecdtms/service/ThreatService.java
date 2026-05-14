package com.ecdtms.service;

import com.ecdtms.repository.ThreatRepository;
import java.sql.SQLException;

public class ThreatService {

    private final ThreatRepository threatRepository;

    public ThreatService() {
        this.threatRepository = new ThreatRepository();
    }

    public void registerNewThreat(String id, String description, String severity) throws SQLException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Threat ID cannot be empty");
        }

        String cleanId = id.trim();
        String cleanDesc = (description != null) ? description.trim() : "";
        String cleanSeverity = (severity != null) ? severity.trim() : "LOW";

        threatRepository.addThreat(cleanId, cleanDesc, cleanSeverity);
    }

    public java.util.List<com.ecdtms.model.threat.Threat> getAllThreats() throws SQLException {
        return threatRepository.getAllThreats();
    }

    public void updateThreat(String id, String description, String severity) throws SQLException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Threat ID cannot be empty");
        }

        String cleanId = id.trim();
        String cleanDesc = (description != null) ? description.trim() : "";
        String cleanSeverity = (severity != null) ? severity.trim() : "LOW";

        threatRepository.updateThreat(cleanId, cleanDesc, cleanSeverity);
    }

    public void deleteThreat(String id) throws SQLException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Threat ID cannot be empty");
        }
        threatRepository.deleteThreat(id.trim());
    }
}
