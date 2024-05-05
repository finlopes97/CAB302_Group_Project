package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    @FXML
    private BorderPane mainLayout;
    @FXML
    private VBox contentArea;
    @FXML
    private Label viewTitle;

    private Map<String, String> pageTitleMap = new HashMap<>();

    public MainController() {
        pageTitleMap.put("/org/trainer/interval_trainer/home-view.fxml", "Home");
        pageTitleMap.put("/org/trainer/interval_trainer/create-routine-view.fxml", "My Routines");
        pageTitleMap.put("/org/trainer/interval_trainer/search-view.fxml", "Find Routines");
        pageTitleMap.put("/org/trainer/interval_trainer/profile-view.fxml", "Profile");
        pageTitleMap.put("/org/trainer/interval_trainer/settings-view.fxml", "Settings");
    }

    public void switchContent(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().setAll(node);
            viewTitle.setText(pageTitleMap.getOrDefault(fxmlFile, "Login"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions, possibly show an error message
        }
    }

    @FXML
    private void onHomeButtonClick() {
        switchContent("/org/trainer/interval_trainer/home-view.fxml");
    }

    @FXML
    private void onMyRoutinesButtonClick() {
        switchContent("/org/trainer/interval_trainer/create-routine-view.fxml");
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