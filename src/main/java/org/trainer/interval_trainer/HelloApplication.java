package org.trainer.interval_trainer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class HelloApplication extends Application {
    private static Stage primaryStage;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT); // Adjust size as needed
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        primaryStage.getScene().setRoot(pane);
    }

    private void createDatabaseFile() {
        String dbFilePath = "src/main/resources/Database.db"; // Relative path
        File dbFile = new File(dbFilePath);
        if (!dbFile.exists()) {
            try {
                if (dbFile.createNewFile()) {
                    System.out.println("Database file created successfully.");

                    // Initialize the User table with one entry
                    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
                         Statement statement = connection.createStatement()) {

                        // Create the User table
                        String createUserTable = "CREATE TABLE IF NOT EXISTS User (" +
                                "Email TEXT PRIMARY KEY," +
                                "Name TEXT," +
                                "Password TEXT" +
                                ");";
                        statement.execute(createUserTable);

                        // Insert the initial user entry
                        String insertUser = "INSERT INTO User (Email, Name, Password) VALUES (" +
                                "'Duncan.zehnder@icloud.com', 'Duncan', 'Cab302!'" +
                                ");";
                        statement.execute(insertUser);

                        System.out.println("User entry added successfully.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("Error creating database file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
