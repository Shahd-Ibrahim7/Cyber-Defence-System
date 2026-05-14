package com.ecdtms.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class NavigationUtil {

    public static void switchScene(ActionEvent event, String fxmlFile) {
        try {
            var url = NavigationUtil.class.getResource("/com/ecdtms/view/" + fxmlFile);
            if (url == null) {
                System.err.println("[NavigationUtil] FXML not found on classpath: /com/ecdtms/view/" + fxmlFile);
                return;
            }

            Parent root = FXMLLoader.load(url);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}