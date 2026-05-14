package com.ecdtms.controller;

import com.ecdtms.service.AuthenticationService;
import com.ecdtms.utils.NavigationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private final AuthenticationService authService = new AuthenticationService();

    @FXML
    public void onLoginClick(ActionEvent event) {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Validation Error", "Please fill all fields");
            return;
        }

        try {
            boolean success = authService.login(username, password);

            if (success) {
                NavigationUtil.switchScene(event, "dashboard.fxml");
            } 
            else {
                showError("Login Failed", "Invalid username or password");
            }

        } catch (SQLException e) {
            showError("Database Error", e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}