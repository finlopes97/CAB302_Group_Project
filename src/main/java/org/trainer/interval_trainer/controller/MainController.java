package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane mainLayout;

    @FXML
    private VBox contentArea; // Make sure this is correctly fx:id linked to your FXML

    public void switchContent(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().setAll(node);
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
        switchContent("/org/trainer/interval_trainer/create-routines-view.fxml");
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