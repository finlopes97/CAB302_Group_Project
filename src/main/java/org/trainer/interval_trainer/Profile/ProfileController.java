package org.trainer.interval_trainer.Profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;

public class ProfileController {

    @FXML
    private ImageView profileImage;

    @FXML
    private Button editButton;

    @FXML
    private void initialize() {
        // Initialize the profile page (load user data, set profile image, etc.)
    }

    @FXML
    private void handleEditButton(ActionEvent event) {
        // Handle the edit button action (switch to edit profile page)
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        // Handle the change password button action
    }

    public void handleBackButton(ActionEvent actionEvent) {
        try {
            HelloApplication.changeScene("hello-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
