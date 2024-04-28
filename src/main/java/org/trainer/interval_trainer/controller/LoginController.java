package org.trainer.interval_trainer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onSignUpPageButton() throws IOException {
        HelloApplication.changeScene("Sign-up.fxml");
    }

    public void onLoginButton() throws IOException {
        HelloApplication.changeScene("hello-view.fxml");
    }

}