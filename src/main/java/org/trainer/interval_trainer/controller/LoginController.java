package org.trainer.interval_trainer.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller for managing the login functionality.
 * This class handles interactions from the login view, including field visibility toggling,
 * user authentication, and navigation between the login and sign-up views.
 */
public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField visiblePasswordField;
    @FXML
    private CheckBox hidePasswordCheckbox;
    @FXML
    private Label errorMessageLabel;

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";

    private static final String INCORRECT_DETAILS = "Some of your details may be incorrect. Please try again.";

    /**
     * Initializes the controller. Sets up the property bindings for password visibility,
     * allowing the password to be shown or hidden based on the state of a CheckBox.
     */
    public void initialize() {
        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());
        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());
        passwordField.visibleProperty().bind(hidePasswordCheckbox.selectedProperty());
        visiblePasswordField.visibleProperty().bind(hidePasswordCheckbox.selectedProperty().not());
    }

    /**
     * Toggles the visibility of the password based on the user's selection of the hidePasswordCheckbox.
     * If the checkbox is checked, the password is obscured in a PasswordField. If unchecked, it is shown in a TextField.
     */
    public void togglePasswordVisibility() {
        if (passwordField.visibleProperty().isBound()) {
            passwordField.visibleProperty().unbind();
        }
        if (visiblePasswordField.visibleProperty().isBound()) {
            visiblePasswordField.visibleProperty().unbind();
        }

        boolean showPassword = hidePasswordCheckbox.isSelected();
        passwordField.setVisible(showPassword);
        visiblePasswordField.setVisible(!showPassword);
    }

    /**
     * Redirects the user to the sign-up page upon request.
     * @throws IOException if unable to load the sign-up view FXML.
     */
    public void onSignUpPageButton() throws IOException {
        HelloApplication.changeScene("sign-up.fxml");
    }

    /**
     * Attempts to log the user in by validating their credentials against the database.
     * If credentials are valid, transitions to the main view of the application, otherwise, displays an error message.
     */
    public void onLoginButton() {
        Platform.runLater(() -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            if (validateCredentials(email, password)) {
                try {
                    HelloApplication.changeScene("main-view.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorMessageLabel.setText(INCORRECT_DETAILS);
            }
        });
    }

    /**
     * Validates the user's credentials by querying the database.
     * @param email User's email to be checked.
     * @param password User's password to be verified.
     * @return true if the credentials are correct and exist in the database, otherwise false.
     */
    private boolean validateCredentials(String email, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Email = ? AND Password = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
