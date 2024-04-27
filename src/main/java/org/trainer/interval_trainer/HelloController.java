package org.trainer.interval_trainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.trainer.interval_trainer.Model.IRoutinesDAO;
import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
import org.trainer.interval_trainer.Model.Routine;

import java.io.IOException;

public class HelloController {
    private IRoutinesDAO routinesDAO;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void onNewRoutineButton() throws IOException {
        HelloApplication.changeScene("new-routine-view.fxml");
    }

    public void onActivity(ActionEvent actionEvent) throws IOException {
        HelloApplication.changeScene("activity-page-view.fxml");
    }
    public HelloController() {
        routinesDAO = new SqliteRoutinesDAO();
    }
}