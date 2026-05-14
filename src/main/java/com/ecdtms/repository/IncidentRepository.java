package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncidentRepository {

    public void logIncident(String title,
                            String deviceId,
                            String threatId,
                            int reportedBy,
                            String status) throws SQLException {

        String query =
                "INSERT INTO incidents (title, device_id, threat_id, reported_by, status) VALUES (?, ?, ?, ?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, title);
            pstmt.setString(2, deviceId);
            pstmt.setString(3, threatId);
            pstmt.setInt(4, reportedBy);
            pstmt.setString(5, status);

            pstmt.executeUpdate();
        }
    }

    public java.util.List<com.ecdtms.model.incident.Incident> getAllIncidents() throws SQLException {
        // Note: Incident model expects Device/Threat objects; we map only the IDs and keep nulls for full objects.
        String query = "SELECT incident_id, title, device_id, threat_id, status, timestamp FROM incidents";

        Connection conn = DatabaseManager.getConnection();
        java.util.List<com.ecdtms.model.incident.Incident> result = new java.util.ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             java.sql.ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int incidentId = rs.getInt("incident_id");
                String title = rs.getString("title");
                String deviceId = rs.getString("device_id");
                String threatId = rs.getString("threat_id");
                String statusStr = rs.getString("status");

                com.ecdtms.model.incident.IncidentStatus status;
                try {
                    status = com.ecdtms.model.incident.IncidentStatus.valueOf(statusStr);
                } catch (Exception e) {
                    status = com.ecdtms.model.incident.IncidentStatus.OPEN;
                }

                // minimal Threat mapping for constructor; Threat is abstract.
                com.ecdtms.model.threat.SeverityLevel sev = com.ecdtms.model.threat.SeverityLevel.LOW;
                com.ecdtms.model.threat.Threat threat = new com.ecdtms.model.threat.Threat(threatId, "", sev) {
                    @Override
                    public void performAttack() {
                        // no-op
                    }
                };

                // We don't have device/timestamp mapping helpers in repo right now; pass null device.
                com.ecdtms.model.incident.Incident incident = new com.ecdtms.model.incident.Incident(incidentId, title, null, threat);
                incident.updateStatus(status);
                result.add(incident);
            }
        }

        return result;
    }

    public void updateIncident(int incidentId,
                                 String title,
                                 String deviceId,
                                 String threatId,
                                 String status) throws SQLException {

        String query =
                "UPDATE incidents SET title = ?, device_id = ?, threat_id = ?, status = ? WHERE incident_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, deviceId);
            pstmt.setString(3, threatId);
            pstmt.setString(4, status);
            pstmt.setInt(5, incidentId);
            pstmt.executeUpdate();
        }
    }

    public void deleteIncident(int incidentId) throws SQLException {
        String query = "DELETE FROM incidents WHERE incident_id = ?";

        Connection conn = DatabaseManager.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, incidentId);
            pstmt.executeUpdate();
        }
    }

    public void updateIncidentStatus(int incidentId,
                                     String status) throws SQLException {

        String query =
                "UPDATE incidents SET status = ? WHERE incident_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, incidentId);

            pstmt.executeUpdate();
        }
    }
}
