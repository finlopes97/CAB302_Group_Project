package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.Session;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.controller.MainController.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Controller for managing the display of user-specific routines within the application.
 * This controller is responsible for loading and displaying routines created by the currently logged-in user.
 */
public class MyRoutinesController {

    private Session session;
    private User currentUser;

    @FXML
    private GridPane myRoutinesGrid;

    /**
     * Initializes the controller and populates the GridPane with the user's routines.
     */
    @FXML
    protected void initialize() {
        session = Session.getInstance();
        currentUser = session.getCurrentUser();
        populateMyRoutinesBox();
    }

    private MainController mainController;

    /**
     * Sets the MainController instance to enable interaction with the main application controller.
     * @param mainController The main controller instance.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    protected void onCreateRoutinesClick() throws IOException {
        mainController.switchContent("/org/trainer/interval_trainer/create-routine-view.fxml");
    }

    /**
     * Handler for creating new routines. Switches the current content to the create routine view.
     * Another case for the SqliteRoutinesDAO.
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    private void populateMyRoutinesBox() {
        try (Connection connection = DriverManager.getConnection(session.getDB_URL());
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM routines WHERE created_by=? LIMIT 10")) {
            statement.setString(1, currentUser.getEmail());
            try (ResultSet rs = statement.executeQuery()) {
                int row = 1;
                while (rs.next()) {
                    String routineName = rs.getString("name");
                    Timestamp createdOn = rs.getTimestamp("created_on");
                    String description = rs.getString("description");
                    int totalTime = rs.getInt("total_time");

                    // Format creation timestamp
                    LocalDateTime createdDateTime = createdOn.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedCreatedOn = createdDateTime.format(formatter);

                    // Display routine data in the grid
                    myRoutinesGrid.add(new Label(routineName), 0, row);
                    myRoutinesGrid.add(new Label(formattedCreatedOn), 1, row);
                    myRoutinesGrid.add(new Label(description), 2, row);
                    myRoutinesGrid.add(new Label(String.valueOf(totalTime)), 3, row);
                    row++;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving routines: " + e.getMessage());
        }

    }
}