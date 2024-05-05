package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HomeController {
    @FXML
    private GridPane routinesGrid;
    @FXML
    private GridPane suggestedRoutinesGrid;

    @FXML
    protected void initialize() {
        populateRoutinesGrid(); // Populate routines grid
        populateSuggestedRoutinesBox(); // Populate suggestions grid
    }

    private void populateRoutinesGrid() {
        // Dummy data
        String[][] dummyRoutines = {
                {"Routine 1", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 2", "45 mins", "Description of Routine 2", "Week 1"},
                {"Routine 3", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 4", "45 mins", "Description of Routine 2", "Week 1"}
        };

        // Add routines to the grid
        for (int i = 1; i < dummyRoutines.length; i++) {
            String[] routineData = dummyRoutines[i-1];

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
                {"Routine 3", "30 mins", "Description of Routine 1", "Week 2"},
                {"Routine 4", "45 mins", "Description of Routine 2", "Week 1"}
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
}