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
    private Label searchResultLabel;
    @FXML
    private TextField searchField;

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";


    @FXML
    protected void initialize() {
        populateSuggestedRoutinesBox(); // Populate suggestions grid
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
                        Timestamp createdOn = rs.getTimestamp("created_on");
                        String description = rs.getString("description");
                        int totalTime = rs.getInt("total_time");

                        // Display routine data in the grid
                        suggestedRoutinesGrid.add(new Label(routineName), 0, row);
                        suggestedRoutinesGrid.add(new Label(createdBy), 1, row);
                        suggestedRoutinesGrid.add(new Label(createdOn.toString()), 2, row);
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
        // Create a task for the search operation
        Task<Void> searchTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Simulate a search operation (replace with your actual search logic)
                Thread.sleep(2000); // Simulate delay (e.g., fetching data from a database or an external API)

                // Perform the search
                List<String> searchResults = new ArrayList<>();
                try (Connection connection = DriverManager.getConnection(DB_URL);) {
                    String sql = "SELECT * FROM routines WHERE name LIKE ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                        pstmt.setString(1, "%" + query + "%");
                        try (ResultSet rs = pstmt.executeQuery()) {
                            // Iterate over the result set and add results to the list
                            while (rs.next()) {
                                String result = "ID: " + rs.getInt("id") +
                                        ", Name: " + rs.getString("name") +
                                        ", Created By: " + rs.getString("created_by") +
                                        ", Created On: " + rs.getTimestamp("created_on") +
                                        ", Description: " + rs.getString("description") +
                                        ", Total Time: " + rs.getInt("total_time");
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

    // Update the search result label with the search results
    private void updateSearchResultLabel(List<String> searchResults) {
        StringBuilder resultText = new StringBuilder();
        for (String result : searchResults) {
            resultText.append(result).append("\n");
        }
        searchResultLabel.setText(resultText.toString());
    }

}