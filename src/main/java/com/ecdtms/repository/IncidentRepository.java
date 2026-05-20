package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;
import com.ecdtms.model.incident.Incident;
import com.ecdtms.model.incident.IncidentStatus;
import com.ecdtms.model.network.Device;
import com.ecdtms.model.network.DeviceStatus;
import com.ecdtms.model.threat.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidentRepository {

    //-----------------------------------
    // CREATE
    //-----------------------------------
    public void logIncident(String title,
                            String deviceId,
                            String threatId,
                            int reportedBy,
                            String status) throws SQLException {

        String query = "INSERT INTO incidents (title, device_id, threat_id, reported_by, status) VALUES (?, ?, ?, ?, ?)";

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

    //-----------------------------------
    // READ ALL
    //-----------------------------------
    public List<Incident> getAllIncidents() throws SQLException {

        String query = "SELECT incident_id, title, device_id, threat_id, status FROM incidents";

        Connection conn = DatabaseManager.getConnection();
        List<Incident> incidents = new ArrayList<>();

        try (
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                int incidentId = rs.getInt("incident_id");
                String title = rs.getString("title");
                String deviceId = rs.getString("device_id");
                String threatId = rs.getString("threat_id");
                String statusStr = rs.getString("status");

                Device device = mapDevice(deviceId);
                Threat threat = mapThreat(threatId);

                Incident incident =
                        new Incident(incidentId, title, device, threat);

                try {
                    incident.updateStatus(
                            IncidentStatus.valueOf(statusStr)
                    );
                } catch (Exception e) {
                    incident.updateStatus(IncidentStatus.OPEN);
                }

                incidents.add(incident);
            }
        }

        return incidents;
    }

    //-----------------------------------
    // READ BY ID
    //-----------------------------------
    public Incident getIncidentById(int id) throws SQLException {

        String query = "SELECT * FROM incidents WHERE incident_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (
                PreparedStatement pstmt = conn.prepareStatement(query)
        ) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    String title = rs.getString("title");
                    String deviceId = rs.getString("device_id");
                    String threatId = rs.getString("threat_id");
                    String statusStr = rs.getString("status");

                    Device device = mapDevice(deviceId);
                    Threat threat = mapThreat(threatId);

                    Incident incident =
                            new Incident(id, title, device, threat);

                    try {
                        incident.updateStatus(
                                IncidentStatus.valueOf(statusStr)
                        );
                    } catch (Exception e) {
                        incident.updateStatus(IncidentStatus.OPEN);
                    }

                    return incident;
                }
            }
        }

        return null;
    }

    //-----------------------------------
    // UPDATE FULL INCIDENT
    //-----------------------------------
    public void updateIncident(int incidentId,
                               String title,
                               String deviceId,
                               String threatId,
                               String status) throws SQLException {

        String query = "UPDATE incidents SET title = ?, device_id = ?, threat_id = ?, status = ? WHERE incident_id = ?";

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

    //-----------------------------------
    // UPDATE STATUS ONLY
    //-----------------------------------
    public void updateIncidentStatus(int incidentId,
                                     String status) throws SQLException {

        String query = "UPDATE incidents SET status = ? WHERE incident_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, incidentId);

            pstmt.executeUpdate();
        }
    }

    //-----------------------------------
    // DELETE
    //-----------------------------------
    public void deleteIncident(int incidentId) throws SQLException {

        String query = "DELETE FROM incidents WHERE incident_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, incidentId);
            pstmt.executeUpdate();
        }
    }

    //-----------------------------------
    // Helper: Map Device
    //-----------------------------------
    private Device mapDevice(String deviceId) {

        if (deviceId == null) {
            return null;
        }

        return new Device(
                deviceId,
                "Unknown-IP",
                DeviceStatus.ACTIVE
        ) {
            @Override
            public void scanVulnerabilities() {
                // temporary dummy implementation
            }
        };
    }

    //-----------------------------------
    // Helper: Map Threat
    //-----------------------------------
    private Threat mapThreat(String threatId) {

        if (threatId == null) {
            return null;
        }

        return new MalwareThreat(
                threatId,
                "Recovered from DB",
                SeverityLevel.LOW,
                "Unknown Malware"
        );
    }
}