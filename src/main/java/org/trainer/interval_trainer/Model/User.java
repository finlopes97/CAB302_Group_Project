package org.trainer.interval_trainer.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code User} class represents a user in the application.
 * It includes methods for retrieving and updating user information from a SQLite database.
 */
public class User {
    private String email;

    /**
     * Constructs a new {@code User} with the specified email address.
     * @param email the email address of the user
     */
    public User(String email) {
        this.email = email;
    }

    /**
     * Gets the email address of the user.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the name of the user from the database.
     * @return the name of the user, or {@code null} if not found
     */
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
            System.err.println("Error: " + e.getMessage());
        }
        return name;
    }

    /**
     * Gets the fitness goal of the user from the database.
     * @return the fitness goal of the user, or {@code null} if not found
     */
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
            System.err.println("Error: " + e.getMessage());
        }
        return fitnessGoal;
    }

    /**
     * Verifies the user's password by comparing it with the stored password hash in the database.
     * @param inputPassword the password to verify
     * @return {@code true} if the password is correct, {@code false} otherwise
     */
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
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Sets the email address of the user in the database and updates the user object.
     * @param email the new email address to set
     */
    public void setEmail(String email) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET Email = ? WHERE Email = ?")) {
            statement.setString(1, email);
            statement.setString(2, this.email);
            statement.executeUpdate();
            this.email = email; // Update the email in the User object
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Sets the name of the user in the database.
     * @param name the new name to set
     */
    public void setName(String name) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET Name = ? WHERE Email = ?")) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Sets the fitness goal of the user in the database.
     * @param fitnessGoal the new fitness goal to set
     */
    public void setFitnessGoal(String fitnessGoal) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET FitnessGoal = ? WHERE Email = ?")) {
            statement.setString(1, fitnessGoal);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Sets the password of the user in the database.
     * @param password the new password to set
     */
    public void setPassword(String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/Database.db");
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE User SET Password = ? WHERE Email = ?")) {
            statement.setString(1, password);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Overrides toString with custom implementation.
     * @return the email address of the user as a string
     */
    @Override
    public String toString() {
        return email;
    }
}
