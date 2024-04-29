package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField EmailInput;
    @FXML
    private TextField PasswordInput;
    @FXML
    private Label ErrorText;

    // JDBC connection details (update with your actual database info)
    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
    public void onSignUpPageButton() throws IOException {
        HelloApplication.changeScene("Sign-up.fxml");
    }

    public void onLoginButton() {
        String email = EmailInput.getText();
        String password = PasswordInput.getText();
        ErrorText.setText("Logging in...");

        // Validate user credentials against the database
        if (validateCredentials(email, password)) {
            // Credentials are valid, change scene to hello view
            try {
                HelloApplication.changeScene("hello-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Credentials are invalid, show an error message (you can customize this)
            ErrorText.setText("Invalid credentials. Please try again.");
        }
    }

    private boolean validateCredentials(String email, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Email = ? AND Password = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If a row is returned, credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
