package com.ecdtms.controller;

import com.ecdtms.model.threat.Threat;
import com.ecdtms.service.DeviceService;
import com.ecdtms.service.IncidentService;
import com.ecdtms.service.ThreatService;
import com.ecdtms.utils.NavigationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class IncidentController {

    @FXML private TextField txtIncidentTitle;

    @FXML private ComboBox<String> comboDevice;
    @FXML private ComboBox<Threat> comboThreat;

    @FXML private ComboBox<String> comboStatus;

    @FXML private TableView<com.ecdtms.model.incident.Incident> incidentsTable;

    @FXML private TableColumn<com.ecdtms.model.incident.Incident, Integer> colIncidentId;
    @FXML private TableColumn<com.ecdtms.model.incident.Incident, String> colTitle;
    @FXML private TableColumn<com.ecdtms.model.incident.Incident, String> colDevice;
    @FXML private TableColumn<com.ecdtms.model.incident.Incident, String> colThreat;
    @FXML private TableColumn<com.ecdtms.model.incident.Incident, String> colStatus;

    private final IncidentService incidentService = new IncidentService();
    private final DeviceService deviceService = new DeviceService();
    private final ThreatService threatService = new ThreatService();

    @FXML
    public void initialize() {

        comboStatus.getItems().setAll(
                "OPEN", "IN_PROGRESS", "RESOLVED", "CLOSED"
        );

        // ================= TABLE =================
        colIncidentId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIncidentId()
                ).asObject()
        );

        colTitle.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTitle()
                )
        );

        colDevice.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getAffectedDevice() != null
                                ? data.getValue().getAffectedDevice().getDeviceId()
                                : ""
                )
        );

        colThreat.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDetectedThreat() != null
                                ? data.getValue().getDetectedThreat().getThreatId()
                                : ""
                )
        );

        colStatus.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStatus() != null
                                ? data.getValue().getStatus().name()
                                : ""
                )
        );

        // ================= ROW SELECT =================
        incidentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {

                txtIncidentTitle.setText(newV.getTitle());

                comboDevice.setValue(

                        newV.getAffectedDevice() != null
                                ? newV.getAffectedDevice().getDeviceId()
                                : null
                );

                comboThreat.setValue(newV.getDetectedThreat()); // 🔥 FIX IMPORTANT

                comboStatus.setValue(
                        newV.getStatus() != null
                                ? newV.getStatus().name()
                                : null
                );
            }
        });

        loadDeviceIds();
        loadThreats();   // 🔥 fixed name
        loadIncidents();
    }

    // ================= SAVE =================
    @FXML
    public void onSaveIncident() {

        String title = txtIncidentTitle.getText();
        String deviceId = comboDevice.getValue();

        Threat threat = comboThreat.getValue(); // 🔥 FIX

        String status = comboStatus.getValue();

        if (title == null || title.trim().isEmpty() || status == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Please fill Incident Title and Status.");
            return;
        }

        if (deviceId == null || deviceId.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Please select a valid Device.");
            return;
        }

        if (threat == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Please select a Threat.");
            return;
        }

        String threatId = threat.getThreatId(); // 🔥 FIX

        try {
            incidentService.createIncident(
                    title,
                    deviceId,
                    threatId,
                    1,
                    status
            );

            showAlert(Alert.AlertType.INFORMATION,
                    "Success",
                    "Incident created successfully.");

            clearForm();
            loadIncidents();

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Database Error",
                    "Failed to create incident: " + e.getMessage());
        }
    }

    // ================= UPDATE =================
    @FXML
    public void onUpdateIncident() {

        com.ecdtms.model.incident.Incident selected =
                incidentsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING,
                    "No selection",
                    "Select an incident row to update.");
            return;
        }

        String title = txtIncidentTitle.getText();
        String deviceId = comboDevice.getValue();

        Threat threat = comboThreat.getValue(); // 🔥 FIX

        String status = comboStatus.getValue();

        if (title == null || title.trim().isEmpty() || status == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Please fill Incident Title and Status.");
            return;
        }

        if (deviceId == null || deviceId.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Please select a valid Device.");
            return;
        }

        if (threat == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Please select a Threat.");
            return;
        }

        String threatId = threat.getThreatId(); // 🔥 FIX

        try {
            incidentService.updateIncident(
                    selected.getIncidentId(),
                    title,
                    deviceId,
                    threatId,
                    status,
                    1
            );

            showAlert(Alert.AlertType.INFORMATION,
                    "Success",
                    "Incident updated successfully.");

            clearForm();
            loadIncidents();

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Database Error",
                    "Failed to update incident: " + e.getMessage());
        }
    }

    // ================= DELETE =================
    @FXML
    public void onDeleteIncident() {

        com.ecdtms.model.incident.Incident selected =
                incidentsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING,
                    "No selection",
                    "Select an incident row to delete.");
            return;
        }

        try {
            incidentService.deleteIncident(selected.getIncidentId(), 1);

            showAlert(Alert.AlertType.INFORMATION,
                    "Success",
                    "Incident deleted successfully.");

            clearForm();
            loadIncidents();

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Database Error",
                    "Failed to delete incident: " + e.getMessage());
        }
    }

    // ================= REFRESH =================
    @FXML
    public void onRefreshIncidents() {
        loadIncidents();
    }

    // ================= LOAD DEVICES =================
    private void loadDeviceIds() {
        try {
            comboDevice.getItems().setAll(deviceService.getAllDeviceIds());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Database Error",
                    "Failed to load device IDs: " + e.getMessage());
        }
    }

    // ================= LOAD THREATS =================
    private void loadThreats() {
        try {
            if (comboThreat == null) {
                // FXML doesn't define comboThreat; prevent FXMLLoader initialize crash.
                return;
            }
            comboThreat.getItems().setAll(threatService.getAllThreats());

            // 🔥 UI display fix
            comboThreat.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Threat item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getThreatId());
                }
            });

            comboThreat.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(Threat item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getThreatId());
                }
            });

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Database Error",
                    "Failed to load threats: " + e.getMessage());
        }
    }

    // ================= LOAD INCIDENTS =================
    private void loadIncidents() {
        try {
            incidentsTable.getItems().setAll(
                    incidentService.getAllIncidents()
            );
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Database Error",
                    "Failed to load incidents: " + e.getMessage());
        }
    }

    // ================= CLEAR =================
    private void clearForm() {
        txtIncidentTitle.clear();
        comboDevice.setValue(null);
        comboThreat.setValue(null);
        comboStatus.setValue(null);
    }

    // ================= ALERT =================
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // ================= BACK =================
    @FXML
    public void onBackClick(ActionEvent event) {
        NavigationUtil.switchScene(event, "dashboard.fxml");
    }
}