package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public boolean validateUser(String username, String password)
            throws SQLException {

        String query =
                "SELECT * FROM users WHERE username = ? AND password = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void addUser(String username,
                        String password,
                        String role,
                        int employeeId) throws SQLException {

        String query =
                "INSERT INTO users (username, password, role, employee_id) VALUES (?, ?, ?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setInt(4, employeeId);

            pstmt.executeUpdate();
        }
    }
}