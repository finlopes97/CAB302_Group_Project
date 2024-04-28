package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.validation.UserRegistrationValidator;

import java.io.IOException;
import java.io.File;
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

    // Other methods and logic...

    public void onSignUpButton() throws ClassNotFoundException, IOException {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate email, username, and password
        UserRegistrationValidator validator = new UserRegistrationValidator();
        if (!validator.isValidEmail(email) || !validator.isValidUsername(username) || !validator.isValidPassword(password)) {
            showErrorMessage("Invalid input. Please check your email, username, and password.");
            return;
        }

        // Create or connect to the users.db file
//        String dbUrl = "jdbc:sqlite:users.db";
//        Class.forName("org.sqlite.JDBC"); // Load the driver
//        try (Connection connection = DriverManager.getConnection(dbUrl)) {
//            // Create the users table if it doesn't exist
//            String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
//                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + "email VARCHAR(100) NOT NULL,"
//                    + "username VARCHAR(50) NOT NULL,"
//                    + "password VARCHAR(50) NOT NULL)";
//            try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
//                statement.executeUpdate();
//            }
//
//            // Insert user data
//            String insertUserSQL = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
//            try (PreparedStatement statement = connection.prepareStatement(insertUserSQL)) {
//                statement.setString(1, email);
//                statement.setString(2, username);
//                statement.setString(3, password);
//                statement.executeUpdate();
//            }

        System.out.println("User registered successfully!");

        HelloApplication.changeScene("hello-view.fxml");

//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
    }

    // Helper method to display error message (you can implement this)
    private void showErrorMessage(String message) {
        // Implement your logic to display the error message to the user
    }

    public void onBackHomeButton() throws IOException {
        HelloApplication.changeScene("login-view.fxml");
    }

}
