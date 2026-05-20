package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;
import com.ecdtms.model.threat.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreatRepository {

    //-----------------------------------
    // CREATE
    //-----------------------------------
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

    //-----------------------------------
    // READ ALL
    //-----------------------------------
    public List<Threat> getAllThreats() throws SQLException {

        String query =
                "SELECT threat_id, description, severity FROM threats";

        Connection conn = DatabaseManager.getConnection();
        List<Threat> result = new ArrayList<>();

        try (
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                String id = rs.getString("threat_id");
                String description = rs.getString("description");
                String severityStr = rs.getString("severity");

                SeverityLevel severity;

                try {
                    severity = SeverityLevel.valueOf(severityStr);
                } catch (Exception e) {
                    severity = SeverityLevel.LOW;
                }

                // temporary generic threat object
                Threat threat = new Threat(id, description, severity) {
                    @Override
                    public void performAttack() {
                        // DB mapping only
                    }
                };

                result.add(threat);
            }
        }

        return result;
    }

    //-----------------------------------
    // READ BY ID
    //-----------------------------------
    public Threat getThreatById(String threatId) throws SQLException {

        String query =
                "SELECT * FROM threats WHERE threat_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, threatId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    String description = rs.getString("description");
                    String severityStr = rs.getString("severity");

                    SeverityLevel severity;

                    try {
                        severity = SeverityLevel.valueOf(severityStr);
                    } catch (Exception e) {
                        severity = SeverityLevel.LOW;
                    }

                    return new Threat(threatId, description, severity) {
                        @Override
                        public void performAttack() {
                            // DB mapping only
                        }
                    };
                }
            }
        }

        return null;
    }

    //-----------------------------------
    // UPDATE
    //-----------------------------------
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

    //-----------------------------------
    // DELETE
    //-----------------------------------
    public void deleteThreat(String threatId) throws SQLException {

        String query =
                "DELETE FROM threats WHERE threat_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, threatId);
            pstmt.executeUpdate();
        }
    }
}