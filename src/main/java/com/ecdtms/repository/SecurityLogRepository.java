package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SecurityLogRepository {

    public void addLog(int userId,
                       String action,
                       String details) throws SQLException {

        String query =
                "INSERT INTO security_logs (user_id, action, details) VALUES (?, ?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, action);
            pstmt.setString(3, details);

            pstmt.executeUpdate();
        }
    }
}