package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThreatRepository {

    public void addThreat(String threatId,
                          String description,
                          String severity) throws SQLException {

        String query =
                "INSERT INTO threats (threat_id, description, severity) VALUES (?, ?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, threatId);
            pstmt.setString(2, description);
            pstmt.setString(3, severity);

            pstmt.executeUpdate();
        }
    }

    public java.util.List<com.ecdtms.model.threat.Threat> getAllThreats() throws SQLException {
        String query = "SELECT threat_id, description, severity FROM threats";

        Connection conn = DatabaseManager.getConnection();
        java.util.List<com.ecdtms.model.threat.Threat> result = new java.util.ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             java.sql.ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Threat is abstract; use a minimal concrete representation via an anonymous subclass.
                String id = rs.getString("threat_id");
                String description = rs.getString("description");
                String severityStr = rs.getString("severity");

                com.ecdtms.model.threat.SeverityLevel severity;
                try {
                    severity = com.ecdtms.model.threat.SeverityLevel.valueOf(severityStr);
                } catch (Exception e) {
                    severity = com.ecdtms.model.threat.SeverityLevel.LOW;
                }

                com.ecdtms.model.threat.Threat threat = new com.ecdtms.model.threat.Threat(id, description, severity) {
                    @Override
                    public void performAttack() {
                        // no-op (DB mapping only)
                    }
                };

                result.add(threat);
            }
        }

        return result;
    }

    public void updateThreat(String threatId,
                               String description,
                               String severity) throws SQLException {

        String query =
                "UPDATE threats SET description = ?, severity = ? WHERE threat_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, description);
            pstmt.setString(2, severity);
            pstmt.setString(3, threatId);
            pstmt.executeUpdate();
        }
    }

    public void deleteThreat(String threatId) throws SQLException {
        String query = "DELETE FROM threats WHERE threat_id = ?";

        Connection conn = DatabaseManager.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, threatId);
            pstmt.executeUpdate();
        }
    }
}
