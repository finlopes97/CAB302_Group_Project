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

import java.io.IOException;
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


    @FXML
    protected void initialize() {
        populateSuggestedRoutinesBox(); // Populate suggestions grid
    }

    private void populateSuggestedRoutinesBox() {
        // Dummy data
        String[][] dummyRoutines = {
                {"Routine 1", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 2", "45 mins", "Description of Routine 2", "Week 1"},
                {"Routine 3", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 4", "45 mins", "Description of Routine 2", "Week 1"},
                {"Routine 5", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 6", "45 mins", "Description of Routine 2", "Week 1"},
                {"Routine 7", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 8", "45 mins", "Description of Routine 2", "Week 1"}
        };

        // Add routines to the grid
        for (int i = 1; i < dummyRoutines.length; i++) {
            String[] routineData = dummyRoutines[i-1];

            for (int j = 0; j < routineData.length; j++) {
                Label label = new Label(routineData[j]);
                suggestedRoutinesGrid.add(label, j, i);
            }
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
                // Replace this with your actual search logic
                searchResults.add("Result 1 for: " + query);
                searchResults.add("Result 2 for: " + query);
                searchResults.add("Result 3 for: " + query);

                // Update the UI with the search results
                updateSearchResultLabel(searchResults);

                return null;
            }
        };

        // Bind the search task to the UI components
        searchResultLabel.textProperty().bind(searchTask.messageProperty());

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