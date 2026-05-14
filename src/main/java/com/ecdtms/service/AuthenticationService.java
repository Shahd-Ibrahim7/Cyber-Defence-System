package com.ecdtms.service;

import com.ecdtms.repository.UserRepository;
import java.sql.SQLException;

public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService() {
        this.userRepository = new UserRepository();
    }

    public boolean login(String username, String password) throws SQLException {
        if (username == null || password == null) return false;

        String cleanUsername = username.trim();
        String cleanPassword = password.trim();

        if (cleanUsername.isEmpty() || cleanPassword.isEmpty()) return false;

        return userRepository.validateUser(cleanUsername, cleanPassword);
    }

    public void registerUser(String username, String password, String role, int employeeId) throws SQLException {
        userRepository.addUser(username.trim(), password.trim(), role, employeeId);
    }
}