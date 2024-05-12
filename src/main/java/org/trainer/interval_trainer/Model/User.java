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

    public void setEmail(String email) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET Email = ? WHERE Email = ?")) {
            statement.setString(1, email);
            statement.setString(2, this.email);
            statement.executeUpdate();
            this.email = email; // Update the email in the User object
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ...

    public void setName(String name) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET Name = ? WHERE Email = ?")) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFitnessGoal(String fitnessGoal) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET FitnessGoal = ? WHERE Email = ?")) {
            statement.setString(1, fitnessGoal);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPassword(String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET Password = ? WHERE Email = ?")) {
            statement.setString(1, password);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Override toString() to return the email address
    @Override
    public String toString() {
        return email;
    }
}
