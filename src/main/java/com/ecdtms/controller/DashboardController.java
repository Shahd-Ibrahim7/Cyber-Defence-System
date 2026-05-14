package com.ecdtms.controller;

import com.ecdtms.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblActiveIncidents;

    @FXML
    private Label lblThreatLevel;

    @FXML
    public void initialize() {
        lblActiveIncidents.setText("12");
        lblThreatLevel.setText("CRITICAL");
    }

    @FXML
    public void onIncidentsClick(ActionEvent event) {
        NavigationUtil.switchScene(event, "incidents.fxml");
    }

    @FXML
    public void onThreatsClick(ActionEvent event) {
        NavigationUtil.switchScene(event, "threats.fxml");
    }

    @FXML
    public void onAlertsClick(ActionEvent event) {
        NavigationUtil.switchScene(event, "alerts.fxml");
    }

    @FXML
    public void onLogoutClick(ActionEvent event) {
        NavigationUtil.switchScene(event, "login.fxml");
    }
}