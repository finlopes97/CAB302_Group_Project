package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.Model.Session;
import org.trainer.interval_trainer.Model.User;

import java.sql.*;

/**
 * Controller for the Home view of the application, handling the display
 * of both user-specific and suggested routines.
 */
public class HomeController {

    @FXML
    private Label welcomeText;
    @FXML
    private GridPane myRoutinesGrid;
    @FXML
    private GridPane suggestedRoutinesGrid;
    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
    private final User currentUser = Session.getInstance().getCurrentUser();

    /**
     * Initializes the controller, populates the grids with routines, and sets the welcome message.
     */
    @FXML
    protected void initialize() {
        populateMyRoutinesBox(); // Populate routines grid
        populateSuggestedRoutinesBox(); // Populate suggestions grid
        welcomeText.setText("Welcome, " + currentUser.getName());
    }

    /**
     * Adds column headings to the specified GridPane.
     * @param grid The GridPane to which the headings will be added.
     */
    private void addHeadings(GridPane grid) {
        String[] headings = {"Name", "Created On", "Type", "Description", "Total Time"};
        for (int i = 0; i < headings.length; i++) {
            Label headingLabel = new Label(headings[i]);
            headingLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;"); // Apply CSS style
            grid.add(headingLabel, i, 0);
        }
    }

    /**
     * Populates the "My Routines" GridPane with routines created by the current user.
     * Should be handled by SqliteRoutineDAO, will fix later.
     */
    private void populateMyRoutinesBox() {
        try {
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM routines WHERE created_by=? LIMIT 10")) {
                statement.setString(1, currentUser.getEmail());
                try (ResultSet rs = statement.executeQuery()) {
                    int row = 1;
                    while (rs.next()) {
                        String routineName = rs.getString("name");
                        String createdBy = rs.getString("created_by");
                        String type = rs.getString("type");
                        String description = rs.getString("description");
                        int totalTime = rs.getInt("total_time");

                        // Add column headings
                        addHeadings(myRoutinesGrid);
                        // Display routine data in the grid
                        myRoutinesGrid.add(new Label(routineName), 0, row);
                        myRoutinesGrid.add(new Label(createdBy), 1, row);
                        myRoutinesGrid.add(new Label(type), 2, row);
                        myRoutinesGrid.add(new Label(description), 3, row);
                        myRoutinesGrid.add(new Label(String.valueOf(totalTime)), 4, row);

                        row++;
                    }
                } catch (SQLException e) {
                    System.err.println("Error retrieving routines: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    /**
     * Populates the "Suggested Routines" GridPane with routines selected from the database.
     * Again, SqliteRoutineDAO should handle this.
     */
    private void populateSuggestedRoutinesBox() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM routines LIMIT 10";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    int row = 1;
                    while (rs.next()) {
                        String routineName = rs.getString("name");
                        String createdBy = rs.getString("created_by");
                        String type = rs.getString("type");
                        String description = rs.getString("description");
                        int totalTime = rs.getInt("total_time");

                        // Add column headings
                        addHeadings(suggestedRoutinesGrid);
                        // Display routine data in the grid
                        suggestedRoutinesGrid.add(new Label(routineName), 0, row);
                        suggestedRoutinesGrid.add(new Label(createdBy), 1, row);
                        suggestedRoutinesGrid.add(new Label(type), 2, row);
                        suggestedRoutinesGrid.add(new Label(description), 3, row);
                        suggestedRoutinesGrid.add(new Label(String.valueOf(totalTime)), 4, row);

                        row++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}