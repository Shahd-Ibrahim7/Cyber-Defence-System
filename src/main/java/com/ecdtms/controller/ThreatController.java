package com.ecdtms.controller;

import com.ecdtms.service.ThreatService;
import com.ecdtms.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.sql.SQLException;

public class ThreatController {

    @FXML private TextField txtThreatId;
    @FXML private TextArea txtDescription;
    @FXML private ChoiceBox<String> choiceSeverity;

    @FXML private TableView<com.ecdtms.model.threat.Threat> threatsTable;
    @FXML private TableColumn<com.ecdtms.model.threat.Threat, String> colThreatId;
    @FXML private TableColumn<com.ecdtms.model.threat.Threat, String> colThreatDescription;
    @FXML private TableColumn<com.ecdtms.model.threat.Threat, String> colThreatSeverity;

    private final ThreatService threatService = new ThreatService();

    @FXML
    public void initialize() {
        choiceSeverity.getItems().setAll(
                "LOW", "MEDIUM", "HIGH", "CRITICAL"
        );

        colThreatId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getThreatId()));
        colThreatDescription.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().toString()));
        colThreatSeverity.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getSeverity() != null ? data.getValue().getSeverity().name() : ""));

        threatsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                txtThreatId.setText(newV.getThreatId());
                txtDescription.setText(newV.toString());
                choiceSeverity.setValue(newV.getSeverity() != null ? newV.getSeverity().name() : null);
            }
        });

        loadThreats();
    }

    @FXML
    public void onAddThreat() {
        String id = txtThreatId.getText();
        String desc = txtDescription.getText();
        String severity = choiceSeverity.getValue();

        if (id == null || id.trim().isEmpty() || desc == null || desc.trim().isEmpty() || severity == null) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Please fill Threat ID, Description and Severity.");
            return;
        }

        try {
            threatService.registerNewThreat(id, desc, severity);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Threat added successfully.");
            clearForm();
            loadThreats();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to add threat: " + e.getMessage());
        }
    }

    @FXML
    public void onRefreshThreats() {
        loadThreats();
    }

    @FXML
    public void onDeleteThreat() {
        com.ecdtms.model.threat.Threat selected = threatsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No selection", "Select a threat to delete.");
            return;
        }

        try {
            threatService.deleteThreat(selected.getThreatId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Threat deleted successfully.");
            clearForm();
            loadThreats();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to delete threat: " + e.getMessage());
        }
    }

    @FXML
    public void onUpdateThreat() {
        com.ecdtms.model.threat.Threat selected = threatsTable.getSelectionModel().getSelectedItem();
        String id = txtThreatId.getText();
        String desc = txtDescription.getText();
        String severity = choiceSeverity.getValue();

        if (selected == null || id == null || id.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No selection", "Select a threat row to update.");
            return;
        }
        if (desc == null || desc.trim().isEmpty() || severity == null) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Description and Severity are required.");
            return;
        }

        try {
            threatService.updateThreat(id, desc, severity);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Threat updated successfully.");
            clearForm();
            loadThreats();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update threat: " + e.getMessage());
        }
    }

    private void loadThreats() {
        try {
            threatsTable.getItems().setAll(threatService.getAllThreats());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load threats: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtThreatId.clear();
        txtDescription.clear();
        choiceSeverity.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Back to Dashboard
    @FXML
    public void onBackClick(ActionEvent event) {
        NavigationUtil.switchScene(event, "dashboard.fxml");
    }
}
