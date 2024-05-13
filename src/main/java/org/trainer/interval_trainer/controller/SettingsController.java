package org.trainer.interval_trainer.controller;
import javafx.fxml.FXML;
import org.trainer.interval_trainer.Model.Session;
import java.io.IOException;
import org.trainer.interval_trainer.HelloApplication;

/**
 * Controller for the settings view of the application.
 * This controller handles actions such as logging out of the user session.
 */
public class SettingsController {
    private Session session;
    /**
     * Initializes any necessary settings when the view is loaded.
     * Currently, this method does not perform any operations but can be used
     * for setting initial states or configurations if needed in the future.
     */
    @FXML
    protected void initialize() {
        session = Session.getInstance();
    }

    /**
     * Handles the logout process by clearing the current user session and switching
     * to the login view.
     * @throws IOException If an error occurs during scene transition.
     */
    @FXML
    private void onLogoutButton() throws IOException {
        // Clear the current user from the session
        session.setCurrentUser(null);
        // Switch back to the login view
        HelloApplication.changeScene("login-view.fxml");
    }
}