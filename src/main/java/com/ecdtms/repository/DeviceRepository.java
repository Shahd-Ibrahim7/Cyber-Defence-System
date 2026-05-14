package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeviceRepository {

    public java.util.List<String> getAllDeviceIds() throws SQLException {
        String query = "SELECT device_id FROM devices";
        java.util.List<String> deviceIds = new java.util.ArrayList<>();

        java.sql.Connection conn = DatabaseManager.getConnection();
        try (java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
             java.sql.ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                deviceIds.add(rs.getString("device_id"));
            }
        }

        return deviceIds;
    }

    public void addDevice(String deviceId,
                          String ipAddress,
                          String status) throws SQLException {


        String query =
                "INSERT INTO devices (device_id, ip_address, status) VALUES (?, ?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, deviceId);
            pstmt.setString(2, ipAddress);
            pstmt.setString(3, status);

            pstmt.executeUpdate();
        }
    }

    public void updateDeviceStatus(String deviceId,
                                   String status) throws SQLException {

        String query =
                "UPDATE devices SET status = ? WHERE device_id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, status);
            pstmt.setString(2, deviceId);

            pstmt.executeUpdate();
        }
    }
}