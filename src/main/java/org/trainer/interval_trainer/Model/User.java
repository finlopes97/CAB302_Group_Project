package org.trainer.interval_trainer.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        String name = null;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT Name FROM User WHERE Email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getFitnessGoal() {
        String fitnessGoal = null;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT FitnessGoal FROM User WHERE Email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                fitnessGoal = resultSet.getString("FitnessGoal");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fitnessGoal;
    }

    public boolean verifyPassword(String inputPassword) {
        String storedPasswordHash = null;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT Password FROM User WHERE Email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                storedPasswordHash = resultSet.getString("Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Override toString() to return the email address
    @Override
    public String toString() {
        return email;
    }
}
