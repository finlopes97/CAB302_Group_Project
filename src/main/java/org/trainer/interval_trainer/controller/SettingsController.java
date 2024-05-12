package org.trainer.interval_trainer.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.trainer.interval_trainer.Model.Session;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;

public class SettingsController {
    @FXML
    protected void initialize() {

    }

    @FXML
    private void onLogoutButton() throws IOException {
        // Clear the current user from the session
        Session.getInstance().setCurrentUser(null);
        // Switch back to the login view
        HelloApplication.changeScene("login-view.fxml");
    }
}