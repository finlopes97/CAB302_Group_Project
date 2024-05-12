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

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";

    private static final String USERNAME_ERROR = "Your username must be between 3 and 30 characters and contain no special characters.";
    private static final String EMAIL_ERROR = "Your email address must contain an '@' symbol to be a valid email address.";
    private static final String PASSWORD_ERROR = "Your password must be between 6 and 30 characters and should be a combination of letters, numbers and special characters.";

    /**
     * Initializes the controller class.
     * Sets up bindings for password visibility and manages the properties of password and visible password fields.
     */
    public void initialize() {
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

    private boolean userExists(String email) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM User WHERE Email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Processes the sign-up action when the sign-up button is clicked.
     * It validates the email, username, and password using the UserRegistrationValidator.
     * If any validation fails, it sets the appropriate error message in errorMessageLabel.
     * If all validations pass, it attempts to register the user and handle potential database errors.
     */
    public void onSignUpButton() throws SQLException {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        email = email.toLowerCase();

        UserRegistrationValidator validator = new UserRegistrationValidator();
        if (!validator.isValidUsername(username)) {
            errorMessageLabel.setText("Invalid username. It must be between 4 and 30 characters and contain no special characters.");
        } else if (!validator.isValidEmail(email)) {
            errorMessageLabel.setText("Invalid email address.");
        } else if (!validator.isValidPassword(password)) {
            errorMessageLabel.setText("Invalid password. It must be between 6 and 30 characters and should be a combination of letters, numbers and special characters.");
        } else if (userExists(username, "Name")) {
            errorMessageLabel.setText("A user with this username already exists. Please use a different username.");
        } else if (userExists(email, "Email")) {
            errorMessageLabel.setText("A user with this email already exists. Please use a different email.");
        }  else {
            try {
                registerNewUser(email, username, password);
                errorMessageLabel.setText("");
                User user123 = new User(email);
                Session.getInstance().setCurrentUser(user123);
                HelloApplication.changeScene("main-view.fxml");
            } catch (Exception e) {
                errorMessageLabel.setText("Registration failed. Please try again.");
                e.printStackTrace();
            }
        }
    }

    private boolean userExists(String value, String field) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM User WHERE " + field + " = ?")) {
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }




    /**
     * Attempts to register a new user in the database.
     * Inserts the new user's email, username, and password into the User table.
     * @param email the user's email address
     * @param username the user's chosen username
     * @param password the user's chosen password
     * @throws SQLException if an error occurs during the database operation
     */
    private void registerNewUser(String email, String username, String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO User (Email, Name, Password) VALUES (?, ?, ?)")) {
                    statement.setString(1, email);
                    statement.setString(2, username);
                    statement.setString(3, password);
                    statement.executeUpdate();
                    System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
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
