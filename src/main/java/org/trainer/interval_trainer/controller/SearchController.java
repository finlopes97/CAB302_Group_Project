package org.trainer.interval_trainer.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import org.trainer.interval_trainer.Model.Session;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the search functionality within the application.
 * It handles both the display of suggested routines and the execution of search queries against the database.
 */
public class SearchController {
    private Session session;
    @FXML
    private Button searchButton;
    @FXML
    private GridPane suggestedRoutinesGrid;
    @FXML
    private Label suggestedRoutineLabel;
    @FXML
    private Button cancelButton;

    private boolean isSearchMode = false; // Flag to indicate search mode

    @FXML
    private TextField searchField;

    /**
     * Initializes the controller by populating the grid with suggested routines.
     */
    @FXML
    protected void initialize() {
        session = Session.getInstance();
        populateSuggestedRoutinesBox(); // Populate suggestions grid
    }

    /**
     * Populates the grid pane with suggested routines, adding column headings once.
     */
    private void addHeadings(){
        String[] headings = {"Name", "Created By", "Type", "Description", "Total Time"};
        for (int i = 0; i < headings.length; i++) {
            Label headingLabel = new Label(headings[i]);
            headingLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;"); // Apply CSS style
            suggestedRoutinesGrid.add(headingLabel, i, 0);
        }
    }

    /**
     * Populates the grid pane with suggested routines, adding column headings once.
     */
    private void populateSuggestedRoutinesBox() {
        try (Connection connection = DriverManager.getConnection(session.getDB_URL());
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM routines LIMIT 10");
             ResultSet rs = pstmt.executeQuery()) {
            addHeadings();
            int row = 1;
            while (rs.next()) {
                displayRoutineInGrid(rs, suggestedRoutinesGrid, row++);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving suggested routines: " + e.getMessage());
        }
    }

    /**
     * Displays a routine in the specified grid.
     * @param rs The ResultSet containing the routine data.
     * @param grid The GridPane where the routine will be displayed.
     * @param row The row in the grid where the routine should be added.
     * @throws SQLException If there is an error processing the ResultSet.
     */
    private void displayRoutineInGrid(ResultSet rs, GridPane grid, int row) throws SQLException {
        String routineName = rs.getString("name");
        String createdBy = rs.getString("created_by");
        String type = rs.getString("type");
        String description = rs.getString("description");
        int totalTime = rs.getInt("total_time");

        grid.add(new Label(routineName), 0, row);
        grid.add(new Label(createdBy), 1, row);
        grid.add(new Label(type), 2, row);
        grid.add(new Label(description), 3, row);
        grid.add(new Label(String.valueOf(totalTime)), 4, row);
    }

    /**
     * Handles the search button click event by initiating a search task.
     * @throws IOException If there is an issue executing the search.
     */
    @FXML
    protected void onSearchButtonClick() throws IOException {
        String query = searchField.getText().trim();

        if (!isSearchMode) {
            isSearchMode = true;
            toggleButtonsVisibility();
        }

        Task<List<String>> searchTask = createSearchTask(query);
        new Thread(searchTask).start();
    }

    /**
     * Creates a task for searching the database based on the query.
     * @param query The search query.
     * @return A Task that performs the database search.
     */
    private Task<List<String>> createSearchTask(String query) {
        return new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                List<String> searchResults = new ArrayList<>();
                try (Connection connection = DriverManager.getConnection(session.getDB_URL());
                     PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM routines WHERE name LIKE ?");
                     ResultSet rs = executeQuery(pstmt, query)) {
                    while (rs.next()) {
                        searchResults.add(String.join(",",
                                rs.getString("name"),
                                rs.getString("created_by"),
                                rs.getString("type"),
                                rs.getString("description"),
                                String.valueOf(rs.getInt("total_time"))));
                    }
                }
                Platform.runLater(() -> updateSearchResultLabel(searchResults));
                return searchResults;
            }
        };
    }

    /**
     * Executes a query with the provided prepared statement.
     * @param pstmt The PreparedStatement to execute.
     * @param query The search term to use in the query.
     * @return The ResultSet from the query.
     * @throws SQLException If there is a database error.
     */
    private ResultSet executeQuery(PreparedStatement pstmt, String query) throws SQLException {
        pstmt.setString(1, "%" + query + "%");
        return pstmt.executeQuery();
    }

    /**
     * Clears the search context and repopulates the suggested routines when the cancel button is clicked.
     */
    @FXML
    protected void onCancelButtonClick() {
        searchField.clear();
        suggestedRoutinesGrid.getChildren().clear();
        suggestedRoutineLabel.setText("Suggested Routines");
        populateSuggestedRoutinesBox();
        if (isSearchMode) {
            isSearchMode = false;
            toggleButtonsVisibility();
        }
    }

    /**
     * Updates the grid with search results.
     * @param searchResults The list of search results to display.
     */
    private void updateSearchResultLabel(List<String> searchResults) {
        suggestedRoutinesGrid.getChildren().clear();
        suggestedRoutineLabel.setText("Search Results");
        addHeadings();

        int row = 1;
        for (String result : searchResults) {
            String[] resultParts = result.split(",");

            for (int i = 0; i < resultParts.length; i++) {
                Label label = new Label(resultParts[i]);
                suggestedRoutinesGrid.add(label, i, row);
            }
            row++;
        }
    }

    /**
     * Toggles the visibility of the search and cancel buttons.
     */
    private void toggleButtonsVisibility() {
        searchButton.setVisible(!searchButton.isVisible());
        cancelButton.setVisible(!cancelButton.isVisible());
    }
}