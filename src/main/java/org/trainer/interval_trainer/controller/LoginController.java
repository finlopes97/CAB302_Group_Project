package org.trainer.interval_trainer.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.Session;
import org.trainer.interval_trainer.Model.User;

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
    private User currentUser;

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

    public void onLoginButton() {
        Platform.runLater(() -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            email = email.toLowerCase();

            if (validateCredentials(email, password)) {
                // Retrieve user information from the database based on the email
                User currentUser = retrieveUser(email);
                if (currentUser != null) {
                    Session.getInstance().setCurrentUser(currentUser);
                    navigateToMainView(currentUser);
                } else {
                    // Handle case where user information couldn't be retrieved
                    errorMessageLabel.setText(INCORRECT_DETAILS);
                }
            } else {
                errorMessageLabel.setText(INCORRECT_DETAILS);
            }
        });
    }

    /**
     * Navigates to the main view of the application and sets the current user in the main controller.
     * @param currentUser The user who has successfully logged in.
     */
    private void navigateToMainView(User currentUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/main-view.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            Session.getInstance().setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Retrieves user information from the database based on email.
     * Will refactor this into a UserDAO at some point, works for now.
     * @param email User's email.
     * @return User object if found, otherwise null.
     */
    private User retrieveUser(String email) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // User found, extract user information and create a User object
                String username = resultSet.getString("Email"); // Assuming email is stored in the "Email" column
                return new User(username);
            } else {
                // No user found with the provided email
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null to indicate error
        }
    }

}


