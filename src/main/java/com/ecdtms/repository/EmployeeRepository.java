package com.ecdtms.repository;

import com.ecdtms.database.DatabaseManager;
import com.ecdtms.model.personnel.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {

    public void addEmployee(int id, String name, String email,
                            String department, double salary) throws SQLException {

        String query =
                "INSERT INTO employees (id, name, email, department, salary) VALUES (?, ?, ?, ?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, department);
            pstmt.setDouble(5, salary);

            pstmt.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {

        String query = "DELETE FROM employees WHERE id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public Employee getEmployeeById(int id) throws SQLException {

        String query = "SELECT * FROM employees WHERE id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("department"),
                            rs.getDouble("salary")
                    );
                }
            }
        }

        return null;
    }
}