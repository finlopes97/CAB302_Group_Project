package org.trainer.interval_trainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label header;
    @FXML
    private Button homeButton;
    @FXML
    private Button createRoutinesButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button settingsButton;
    @FXML
    private GridPane routinesGrid;
    @FXML
    private GridPane suggestedRoutinesGrid;

    @FXML
    protected void initialize() {
        welcomeText.setText("Welcome user!");
        header.setText("Interval Training App");
        populateRoutinesGrid(); // Populate routines grid
        populateSuggestedRoutinesBox(); // Populate suggestions grid
    }

    private void populateRoutinesGrid() {
        // Dummy data
        String[][] dummyRoutines = {
                {"Routine 1", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 2", "45 mins", "Description of Routine 2", "Week 1"},
                {"Routine 3", "60 mins", "Description of Routine 3", "Day 3"}
        };

        // Add routines to the grid
        for (int i = 0; i < dummyRoutines.length; i++) {
            String[] routineData = dummyRoutines[i];

            for (int j = 0; j < routineData.length; j++) {
                Label label = new Label(routineData[j]);
                routinesGrid.add(label, j, i);
            }
        }
    }

    private void populateSuggestedRoutinesBox() {
        // Dummy data
        String[][] dummyRoutines = {
                {"Routine 1", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 2", "45 mins", "Description of Routine 2", "Week 1"},
                {"Routine 3", "60 mins", "Description of Routine 3", "Day 3"}
        };

        // Add routines to the grid
        for (int i = 0; i < dummyRoutines.length; i++) {
            String[] routineData = dummyRoutines[i];

            for (int j = 0; j < routineData.length; j++) {
                Label label = new Label(routineData[j]);
                suggestedRoutinesGrid.add(label, j, i);
            }
        }
    }


    @FXML
    protected void onHomeButtonClick() {
        // Create actions later
    }

    @FXML
    protected void onCreateRoutinesButtonClick() {
        // Create actions later
    }

    @FXML
    protected void onSearchButtonClick() {
        // Create actions later
    }
    @FXML
    protected void onProfileButtonClick() {
        // Create actions later
    }

    @FXML
    protected void onSettingsButtonClick() {
        // Create actions later
    }

}