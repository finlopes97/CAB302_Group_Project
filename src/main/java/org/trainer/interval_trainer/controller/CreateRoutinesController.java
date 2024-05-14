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

/**
 * Controller for creating and managing routine creation in the application.
 */
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

    private final User currentUser = Session.getInstance().getCurrentUser();
    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";

    private MainController mainController;

    /**
     * Sets the main controller to handle navigation and content switching within the application.
     * @param mainController The main controller instance.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Handles the action for the back button. It switches the current scene to the my-routine-view.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    protected void onBackButton() throws IOException {
        mainController.switchContent("/org/trainer/interval_trainer/my-routine-view.fxml");
    }

    /**
     * Handles the creation of a new routine based on user inputs from the form.
     * @throws IOException If there is an error switching scenes.
     */
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
//            HelloApplication.changeScene("my-routine-view.fxml");
            mainController.switchContent("/org/trainer/interval_trainer/my-routine-view.fxml");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This should be handled by the SqliteRoutinesDAO class, beyond the scope of this branch.
     */
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