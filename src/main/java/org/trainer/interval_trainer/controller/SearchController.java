package org.trainer.interval_trainer.controller;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.Model.*;
import org.trainer.interval_trainer.controller.manage_routines.RoutineListController;
import org.trainer.interval_trainer.controller.manage_routines.RoutinePreviewController;

import java.util.List;
import java.util.Optional;

public class SearchController extends RoutineListController {
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
    @FXML
    VBox routinesVBox;

    private boolean isSearchMode = false;
    @FXML
    private TextField searchField;

    private SqliteRoutinesDAO dao;

    @FXML
    protected void initialize() {
        dao = new SqliteRoutinesDAO();
        populateSuggestedRoutinesBox();
    }
    private void populateSuggestedRoutinesBox() {
        updateView(); // Call updateView to display recent routines
    }

    private void updateRoutineList(List<Routine> routines) {
        routinesVBox.getChildren().clear();
        for (Routine routine : routines) {
            routinesVBox.getChildren().add(new RoutinePreviewController(routine, this));
        }
    }

    @FXML
    protected void onSearchButtonClick() {
        String query = searchField.getText();

        if (!isSearchMode) {
            isSearchMode = true;
            toggleButtonsVisibility();
        }

        Task<List<Routine>> searchTask = new Task<>() {
            @Override
            protected List<Routine> call() throws Exception {
                return dao.searchRoutine(query);
            }
        };

        searchTask.setOnSucceeded(event -> {
            updateRoutineList(searchTask.getValue());
        });

        new Thread(searchTask).start();
    }

    @FXML
    protected void onCancelButtonClick() {
        searchField.clear();
        populateSuggestedRoutinesBox();

        if (isSearchMode) {
            isSearchMode = false;
            toggleButtonsVisibility();
        }
    }

    private void toggleButtonsVisibility() {
        searchButton.setVisible(!searchButton.isVisible());
        cancelButton.setVisible(!cancelButton.isVisible());
    }

    @Override
    public void updateView() {
        int limit = 5; // Set the limit for the number of routines to retrieve
        List<Routine> routines = dao.getSomeRoutines(Optional.empty(), limit); // Fetch routines without specifying a user
        updateRoutineList(routines); // Update routine list directly in the VBox
    }

}
