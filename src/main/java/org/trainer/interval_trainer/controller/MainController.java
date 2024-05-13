package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.Model.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Main controller for managing the primary UI navigation and content display within the main window of the application.
 * It handles scene switching and updates the main content area based on user interactions.
 */
public class MainController {
    @FXML
    private VBox contentArea;
    @FXML
    private Label viewTitle;
    @FXML
    public Button myRoutines;

    @FXML
    private final Stage stage = HelloApplication.getPrimaryStage();

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    private final Map<String, String> pageTitleMap = new HashMap<>();

    /**
     * Constructor that initializes the title map for various views.
     */
    public MainController() {
        pageTitleMap.put("/org/trainer/interval_trainer/home-view.fxml", "Home");
        pageTitleMap.put("/org/trainer/interval_trainer/my-routine-view.fxml", "My Routines");
        pageTitleMap.put("/org/trainer/interval_trainer/search-view.fxml", "Find Routines");
        pageTitleMap.put("/org/trainer/interval_trainer/profile-view.fxml", "Profile");
        pageTitleMap.put("/org/trainer/interval_trainer/settings-view.fxml", "Settings");
        pageTitleMap.put("/org/trainer/interval_trainer/create-routine-view.fxml", "Create Routine");
    }

    /**
     * Sets the initial content of the application to the home view.
     */
    @FXML
    public void initialize() {
        switchContent("/org/trainer/interval_trainer/home-view.fxml");
    }

    /**
     * Switches the content of the main area to the specified FXML view, updating the title and resizing the stage.
     * @param fxmlFile The FXML file path to load into the main content area.
     */
    public void switchContent(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            contentArea.getChildren().setAll(root);
            viewTitle.setText(pageTitleMap.getOrDefault(fxmlFile, "Home"));

            // Set MainController instance if needed
            if (loader.getController() instanceof MyRoutinesController) {
                ((MyRoutinesController) loader.getController()).setMainController(this);
            } else if (loader.getController() instanceof CreateRoutinesController) {
                ((CreateRoutinesController) loader.getController()).setMainController(this);
            }

            // Set the stage size
            if (stage != null) {
                stage.setWidth(WIDTH);
                stage.setHeight(HEIGHT);
            }

        } catch (IOException e) {
            System.err.println("Failed to load page: " + e.getMessage());
        }
    }

    /**
     * Switches the content of the main area to the home view.
     */
    @FXML
    private void onHomeButtonClick() {
        switchContent("/org/trainer/interval_trainer/home-view.fxml");
    }

    /**
     * Switches the content of the main area to the routines view.
     */
    @FXML
    private void onMyRoutinesButtonClick() {
        switchContent("/org/trainer/interval_trainer/manage_routines/my-routine-view.fxml");
    }

    /**
     * Switches the content of the main area to the search view.
     */
    @FXML
    private void onFindRoutinesButtonClick() {
        switchContent("/org/trainer/interval_trainer/search-view.fxml");
    }

    /**
     * Switches the content of the main area to the profile view.
     */
    @FXML
    private void onProfileButtonClick() {
        switchContent("/org/trainer/interval_trainer/profile-view.fxml");
    }

    @FXML
    private void onSettingsButtonClick() {
        switchContent("/org/trainer/interval_trainer/settings-view.fxml");
    }
}