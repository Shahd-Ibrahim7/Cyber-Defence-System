package com.ecdtms.controller;

import com.ecdtms.service.NotificationService;
import com.ecdtms.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AlertController {

    @FXML private ListView<String> listAlerts;

    private final NotificationService notificationService =
            new NotificationService();

    @FXML
    public void initialize() {
        listAlerts.getItems().addAll(
                "Critical: Server Down",
                "Warning: High Traffic"
        );
    }

    @FXML
    public void onDismissAlert() {

        String selected = listAlerts.getSelectionModel().getSelectedItem();

        if (selected != null) {
            listAlerts.getItems().remove(selected);
        } else {
            System.out.println("No alert selected");
        }
    }

    // Back to Dashboard
    @FXML
    public void onBackClick(ActionEvent event) {
        NavigationUtil.switchScene(event, "dashboard.fxml");
    }
}