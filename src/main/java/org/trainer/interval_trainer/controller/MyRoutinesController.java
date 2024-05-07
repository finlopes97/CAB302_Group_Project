package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class MyRoutinesController {

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
    @FXML
    private Button createRoutineButton;

    @FXML
    protected void initialize() {

    }

    @FXML
    protected void onCreateRoutinesClick() throws IOException {
        HelloApplication.changeScene("create-routine-view.fxml");
    }

}