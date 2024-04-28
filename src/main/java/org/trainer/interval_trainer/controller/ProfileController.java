package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;

public class ProfileController {
    public Label nameLabel;
    public Label emailLabel;
    public Label fitnessGoalLabel;
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
    protected void initialize() {
        welcomeText.setText("Profile");
        nameLabel.setText("Name: Test Name");
        emailLabel.setText("Email: Email@gmail.com");
        fitnessGoalLabel.setText("Fitness Goal:" +
                "" +
                "Doing 70 million jumps in the air with a backflip mixed in");
        header.setText("Interval Training App");
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
        if (currentScene.getRoot().getId().equals("createRoutineRoot")) {
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

}