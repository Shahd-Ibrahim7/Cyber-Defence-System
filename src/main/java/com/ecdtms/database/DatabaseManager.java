package com.ecdtms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL =
            "jdbc:mysql://localhost:3306/ecdtms_db?useSSL=false&serverTimezone=UTC";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );

            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL Driver not found.", e);
            }
        }

        return connection;
    }

    public static void closeConnection() {

        if (connection != null) {

            try {
                if (!connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean testConnection() {

        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}