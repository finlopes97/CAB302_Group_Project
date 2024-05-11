package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.Model.Session;
import java.io.IOException;
import org.trainer.interval_trainer.Model.User;

public class ProfileController {
    public Label nameLabel;
    public Label emailLabel;
    public Label fitnessGoalLabel;
    @FXML
    protected void initialize() throws IOException {
        User currentUser = Session.getInstance().getCurrentUser();
        if (currentUser != null) {
            nameLabel.setText("Name: " + currentUser.getName());
            emailLabel.setText("Email: " + currentUser.getEmail());
            // Assuming the User model has a getFitnessGoal method
            if (currentUser.getFitnessGoal() != null) {
                fitnessGoalLabel.setText("Fitness Goal: " + currentUser.getFitnessGoal());
            }
            else {
                fitnessGoalLabel.setText("Fitness Goal: No current fitness goal");
            }
        }
    }


}