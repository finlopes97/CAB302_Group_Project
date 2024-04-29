package org.trainer.interval_trainer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;

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
    private Button activityButton;
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


    @FXML
    protected void onHomeButtonClick() throws IOException {
        // Get the current scene
        Scene currentScene = homeButton.getScene();

        // Check if the current scene is already the "create-routine-view.fxml"
        if (currentScene.getRoot().getId().equals("helloRoot")) {
            // Already on the new routine page, no need to reload
            return;
        }

        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }


    @FXML
    protected void onCreateRoutinesButtonClick() throws IOException {
        // Get the current scene
        Scene currentScene = createRoutinesButton.getScene();

        // Check if the current scene is already the "create-routine-view.fxml"
        if (currentScene.getRoot().getId().equals("newRoutineRoot")) {
            // Already on the new routine page, no need to reload
            return;
        }
        Stage stage = (Stage) createRoutinesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-routine-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSearchButtonClick() throws IOException {
        // Get the current scene
        Scene currentScene = searchButton.getScene();

        // Check if the current scene is already the "create-routine-view.fxml"
        if (currentScene.getRoot().getId().equals("searchRoot")) {
            // Already on the new routine page, no need to reload
            return;
        }
        Stage stage = (Stage) searchButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void onProfileButtonClick() throws IOException {
        // Get the current scene
        Scene currentScene = profileButton.getScene();

        // Check if the current scene is already the "create-routine-view.fxml"
        if (currentScene.getRoot().getId().equals("profileRoot")) {
            // Already on the new routine page, no need to reload
            return;
        }
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        // Get the current scene
        Scene currentScene = settingsButton.getScene();

        // Check if the current scene is already the "create-routine-view.fxml"
        if (currentScene.getRoot().getId().equals("settingsRoot")) {
            // Already on the new routine page, no need to reload
            return;
        }
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void onActivityButtonClick() throws IOException {
        // Get the current scene
        Scene currentScene = searchButton.getScene();

        // Check if the current scene is already the "create-routine-view.fxml"
        if (currentScene.getRoot().getId().equals("activityRoot")) {
            // Already on the new routine page, no need to reload
            return;
        }
        Stage stage = (Stage) searchButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("activity-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

}