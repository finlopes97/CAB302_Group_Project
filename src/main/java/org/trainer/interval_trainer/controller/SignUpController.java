package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.validation.UserRegistrationValidator;
import org.trainer.interval_trainer.Model.Session;

import java.io.IOException;
import java.sql.*;

/**
 * Controller for managing the sign-up process.
 * This class handles user interactions from the sign-up form, validates user input,
 * manages visibility of password fields, and handles user registration with database interaction.
 */
public class SignUpController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField visiblePasswordField;
    @FXML
    private CheckBox hidePasswordCheckbox;
    @FXML
    private Label errorMessageLabel;

    private Session session;

    private static final String USERNAME_ERROR = "Your username must be between 3 and 30 characters and contain no special characters.";
    private static final String EMAIL_ERROR = "Your email address must contain an '@' symbol to be a valid email address.";
    private static final String PASSWORD_ERROR = "Your password must be between 6 and 30 characters and should be a combination of letters, numbers and special characters.";

    /**
     * Initializes the controller class.
     * Sets up bindings for password visibility and manages the properties of password and visible password fields.
     */
    public void initialize() {
        session = Session.getInstance();

        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());
        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());
        passwordField.visibleProperty().bind(hidePasswordCheckbox.selectedProperty());
        visiblePasswordField.visibleProperty().bind(hidePasswordCheckbox.selectedProperty().not());
    }

    /**
     * Toggles the visibility of the password field based on the checkbox selection.
     * If the checkbox is selected, the password is obscured. Otherwise, it is visible.
     */
    public void togglePasswordVisibility() {
        boolean showPassword = hidePasswordCheckbox.isSelected();
        passwordField.setVisible(!showPassword);
        visiblePasswordField.setVisible(showPassword);
    }

    /**
     * Handles user registration when the sign-up button is clicked.
     * Validates input fields and checks if the user already exists before attempting to register a new user.
     * @throws SQLException If an error occurs during database interaction.
     */
    public void onSignUpButton() throws SQLException {
        String email = emailField.getText().trim().toLowerCase();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        UserRegistrationValidator validator = new UserRegistrationValidator();
        if (!validator.isValidUsername(username)) {
            errorMessageLabel.setText(USERNAME_ERROR);
        } else if (!validator.isValidEmail(email)) {
            errorMessageLabel.setText(EMAIL_ERROR);
        } else if (!validator.isValidPassword(password)) {
            errorMessageLabel.setText(PASSWORD_ERROR);
        } else if (userExists(username, "Name")) {
            errorMessageLabel.setText("A user with this username already exists. Please use a different username.");
        } else if (userExists(email, "Email")) {
            errorMessageLabel.setText("A user with this email already exists. Please use a different email.");
        } else {
            try {
                registerNewUser(email, username, password);
                Session.getInstance().setCurrentUser(new User(email));  // Simplified user session handling
                HelloApplication.changeScene("main-view.fxml");
            } catch (Exception e) {
                errorMessageLabel.setText("Registration failed. Please try again.");
                System.err.println("Error registering user: " + e.getMessage());
            }
        }
    }

    /**
     * Checks if a user already exists in the database with the given value in the specified field.
     * @param value The value to check in the database (username or email).
     * @param field The database field to check (Name or Email).
     * @return true if the user exists, false otherwise.
     * @throws SQLException If an error occurs during the query.
     */
    private boolean userExists(String value, String field) throws SQLException {
        try (Connection connection = DriverManager.getConnection(session.getDB_URL());
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM User WHERE " + field + " = ?")) {
            statement.setString(1, value);
            return statement.executeQuery().next();
        }
    }

    /**
     * Registers a new user in the database.
     * @param email The email address of the new user.
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @throws SQLException If an error occurs during the insertion.
     */
    private void registerNewUser(String email, String username, String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection(session.getDB_URL());
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO User (Email, Name, Password) VALUES (?, ?, ?)")) {
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.executeUpdate();
        }
    }

    /**
     * Handles the action to go back to the login view.
     * @throws IOException if an error occurs when changing scenes.
     */
    public void onBackHomeButton() throws IOException {
        HelloApplication.changeScene("login-view.fxml");
    }
}
