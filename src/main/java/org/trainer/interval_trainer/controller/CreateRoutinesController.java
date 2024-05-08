package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.validation.UserRegistrationValidator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class CreateRoutinesController {
    @FXML
    private TextField routineNameField;
    @FXML
    private TextField createdByField;
    @FXML
    private DatePicker timeCreatedField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField timeField;
    @FXML
    private Button backButton;


    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";

    @FXML
    protected void initialize() {

    }

    public void onBackButton() throws IOException {
        HelloApplication.changeScene("my-routine-view.fxml");
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("my-routine-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
//        stage.setScene(scene);
    }

    public void onNewRoutineButton() {
        // Get the selected date from the DatePicker
        LocalDate selectedDate = timeCreatedField.getValue();

        String routineName = routineNameField.getText();
        String routineCreator = createdByField.getText(); // Should automatically set this by checking logged-in user.
        Timestamp timeCreated = Timestamp.valueOf(selectedDate.atStartOfDay());
        String description = descriptionField.getText();
        // Convert text to integer
        int time = Integer.parseInt(timeField.getText());

            try {
                addRoutine(routineName, routineCreator, timeCreated, description, time);
//                errorMessageLabel.setText("");
                HelloApplication.changeScene("my-routine-view.fxml");
            } catch (Exception e) {
//                errorMessageLabel.setText("Registration failed. Please try again.");
                e.printStackTrace();
            }
        }


    private void addRoutine(String routineName, String routineCreator, Timestamp timeCreated, String description, int time) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO routines (name, created_by, created_on, description, total_time) VALUES (?,?,?,?,?)");
            statement.setString(1, routineName);
            statement.setString(2, routineCreator);
            statement.setTimestamp(3, timeCreated);
            statement.setString(4, description);
            statement.setInt(5, time);
            statement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}