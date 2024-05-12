package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.Model.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainController {
//    @FXML
//    private BorderPane mainLayout;
    @FXML
    private VBox contentArea;
    @FXML
    private Label viewTitle;
    @FXML
    public Button myRoutines;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    private final Map<String, String> pageTitleMap = new HashMap<>();

    public MainController() {
        pageTitleMap.put("/org/trainer/interval_trainer/home-view.fxml", "Home");
        pageTitleMap.put("/org/trainer/interval_trainer/manage_routines/my-routine-view.fxml", "My Routines");
        pageTitleMap.put("/org/trainer/interval_trainer/search-view.fxml", "Find Routines");
        pageTitleMap.put("/org/trainer/interval_trainer/profile-view.fxml", "Profile");
        pageTitleMap.put("/org/trainer/interval_trainer/settings-view.fxml", "Settings");
    }
    private User currentUser; // Field to store the current user
    /**
     * Set the current user in the controller.
     * @param user The user to set as the current user.
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @FXML
    public void initialize() {
        switchContent("/org/trainer/interval_trainer/home-view.fxml");
    }

    public void switchContent(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            contentArea.getChildren().setAll(node);
            viewTitle.setText(pageTitleMap.getOrDefault(fxmlFile, "Home"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onHomeButtonClick() {
        switchContent("/org/trainer/interval_trainer/home-view.fxml");
    }

    @FXML
    private void onMyRoutinesButtonClick() {
        switchContent("/org/trainer/interval_trainer/manage_routines/my-routine-view.fxml");
    }

    @FXML
    private void onFindRoutinesButtonClick() {
        switchContent("/org/trainer/interval_trainer/search-view.fxml");
    }

    @FXML
    private void onProfileButtonClick() {
        switchContent("/org/trainer/interval_trainer/profile-view.fxml");
    }

    @FXML
    private void onSettingsButtonClick() {
        switchContent("/org/trainer/interval_trainer/settings-view.fxml");
    }
}