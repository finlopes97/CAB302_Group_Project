package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;

public class ProfileController {
    public Label nameLabel;
    public Label emailLabel;
    public Label fitnessGoalLabel;

    @FXML
    protected void initialize() {
        nameLabel.setText("Name: Test Name");
        emailLabel.setText("Email: Email@gmail.com");
        fitnessGoalLabel.setText("Fitness Goal:" +
                "" +
                "Doing 70 million jumps in the air with a backflip mixed in");
    }
}