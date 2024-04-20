package org.trainer.interval_trainer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.trainer.interval_trainer.NewRoutine.Routine;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void onNewRoutineButton() throws IOException {
        HelloApplication.setRoot(new Routine());
    }
}