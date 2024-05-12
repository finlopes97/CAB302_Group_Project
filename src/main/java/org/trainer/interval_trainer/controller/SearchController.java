package org.trainer.interval_trainer.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import javafx.application.Platform;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label header;
    @FXML
    private Button homeButton;
    @FXML
    private Button createRoutinesButton;
    @FXML
    private Button searchPageButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button settingsButton;
    @FXML
    private GridPane suggestedRoutinesGrid;
    @FXML
    private Label suggestedRoutineLabel;
    @FXML
    private Button cancelButton;

//    @FXML
//    private Label searchResultLabel;
    private boolean isSearchMode = false; // Flag to indicate search mode

    @FXML
    private TextField searchField;

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";


    @FXML
    protected void initialize() {
        populateSuggestedRoutinesBox(); // Populate suggestions grid
    }

    private void addHeadings(GridPane grid){
        String[] headings = {"Name", "Created On", "Type", "Description", "Total Time"};
        for (int i = 0; i < headings.length; i++) {
            Label headingLabel = new Label(headings[i]);
            headingLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;"); // Apply CSS style
            suggestedRoutinesGrid.add(headingLabel, i, 0);
        }
    }

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


    @FXML
    protected void onSearchButtonClick() throws IOException {
        String query = searchField.getText();

        if (!isSearchMode) { // If not in search mode, switch to search mode
            isSearchMode = true;
            toggleButtonsVisibility(); // Swap search and cancel buttons
        }
        // Create a task for the search operation
        Task<Void> searchTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Perform the search
                List<String> searchResults = new ArrayList<>();
                try (Connection connection = DriverManager.getConnection(DB_URL);) {
                    String sql = "SELECT * FROM routines WHERE name LIKE ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                        pstmt.setString(1, "%" + query + "%");
                        try (ResultSet rs = pstmt.executeQuery()) {
                            // Iterate over the result set and add results to the list
                            while (rs.next()) {
                                String result =
                                        "" + rs.getString("name") +
                                        "," + rs.getString("created_by") +
                                        "," + rs.getString("type") +
                                        "," + rs.getString("description") +
                                        "," + rs.getInt("total_time");
                                searchResults.add(result);
                                System.out.println(result);

                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle potential SQL exceptions
                }

                // Update the UI with the search results
                Platform.runLater(() -> {
                    updateSearchResultLabel(searchResults);
                });


                return null;
            }
        };

        // Bind the search task to the UI components
//        searchResultLabel.textProperty().bind(searchTask.messageProperty());

        // Execute the search task
        new Thread(searchTask).start();
    }

    @FXML
    protected void onCancelButtonClick() {
        // Clear the search field
        searchField.clear();
        // Clear the suggestedRoutinesGrid
        suggestedRoutinesGrid.getChildren().clear();
        // Clear the suggestedRoutineLabel
        suggestedRoutineLabel.setText("Suggested Routines");

        populateSuggestedRoutinesBox(); // Populate suggestions grid

        if (isSearchMode) { // If in search mode, switch back to normal mode
            isSearchMode = false;
            toggleButtonsVisibility(); // Swap search and cancel buttons
        }
    }

    // Update the search result label with the search results
    private void updateSearchResultLabel(List<String> searchResults) {
        // Clear the suggestedRoutinesGrid
        suggestedRoutinesGrid.getChildren().clear();

        // Clear the searchResultLabel
        suggestedRoutineLabel.setText("Search Results");

        // Add column headings
        addHeadings(suggestedRoutinesGrid);

        // Populate the suggestedRoutinesGrid with the search results
        int row = 1;
        for (String result : searchResults) {
            String[] resultParts = result.split(","); // Split the result into parts

            // Add each part to the grid
            for (int i = 0; i < resultParts.length; i++) {
                Label label = new Label(resultParts[i]);
                suggestedRoutinesGrid.add(label, i, row);
            }
            row++;
        }
    }

    // Method to toggle visibility of search and cancel buttons
    private void toggleButtonsVisibility() {
        searchButton.setVisible(!searchButton.isVisible());
        cancelButton.setVisible(!cancelButton.isVisible());
    }

}