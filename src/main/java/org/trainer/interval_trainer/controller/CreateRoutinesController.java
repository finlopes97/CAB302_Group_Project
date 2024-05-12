package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.Model.Session;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.validation.UserRegistrationValidator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class CreateRoutinesController {
    @FXML
    private TextField routineNameField;
    @FXML
    private TextField routineTypeField;
    @FXML
    private Spinner<Integer> intervalNum;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Spinner<Integer> mins;
    @FXML
    private Spinner<Integer> secs;


    @FXML
    private Button backButton;

    // Add a field to hold the current user
    private final User currentUser = Session.getInstance().getCurrentUser();


    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";


    @FXML
    protected void initialize() {
    }



//    public void onBackButton() throws IOException {
//        HelloApplication.changeScene("my-routine-view.fxml");
////        main.switchContent("my-routine-view.fxml");
//    }


    private MainController mainController;

    // Method to set the MainController instance
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    protected void onBackButton() throws IOException {
        mainController.switchContent("/org/trainer/interval_trainer/my-routine-view.fxml");
    }

    public void onCreateRoutineButton() throws IOException {
        // Get the selected date from the DatePicker
        LocalDate selectedDate = LocalDate.now();
        String routineName = routineNameField.getText();
        String routineCreator = currentUser != null ? currentUser.getEmail() : "Unknown"; // Check if currentUser is null
        String routineType = routineTypeField.getText();
        Timestamp timeCreated = Timestamp.valueOf(selectedDate.atStartOfDay());
        String description = descriptionField.getText();
        int numIntervals = intervalNum.getValue();

        int intervalTime = mins.getValue() * 60 + secs.getValue(); // in seconds
        int totalTime = numIntervals * intervalTime; // in seconds

        try {
            addRoutine(routineName, routineType, routineCreator, timeCreated, description, numIntervals, intervalTime, totalTime);
            HelloApplication.changeScene("my-routine-view.fxml");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addRoutine(String routineName, String routineType, String routineCreator, Timestamp timeCreated, String description, int numIntervals, int intervalTime, int totalTime) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO routines (name, type, created_by, created_on, description, num_intervals, interval_time, total_time) VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, routineName);
            statement.setString(2, routineType);
            statement.setString(3, routineCreator);
            statement.setTimestamp(4, timeCreated);
            statement.setString(5, description);
            statement.setInt(6, numIntervals);
            statement.setInt(7, intervalTime);
            statement.setInt(8, totalTime);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}