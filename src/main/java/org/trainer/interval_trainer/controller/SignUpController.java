package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.validation.UserRegistrationValidator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";

    public void onSignUpButton() {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        UserRegistrationValidator validator = new UserRegistrationValidator();
        if (!validator.isValidEmail(email) || !validator.isValidUsername(username) || !validator.isValidPassword(password)) {
            System.out.println("Invalid input. Please check your email, username, and password.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO User (Email, Name, Password) VALUES (?, ?, ?)")) {
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.executeUpdate();
            System.out.println("User registered successfully!");
            HelloApplication.changeScene("main-view.fxml");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackHomeButton() throws IOException {
        HelloApplication.changeScene("login-view.fxml");
    }
}
