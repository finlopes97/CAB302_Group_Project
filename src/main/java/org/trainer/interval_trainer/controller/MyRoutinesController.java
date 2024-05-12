package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.Session;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.controller.MainController.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MyRoutinesController {

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
    private final User currentUser = Session.getInstance().getCurrentUser();

    @FXML
    private GridPane myRoutinesGrid;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    @FXML
    protected void initialize() {
        populateMyRoutinesBox();
    }

    private MainController mainController;

    // Method to set the MainController instance
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    protected void onCreateRoutinesClick() throws IOException {
        mainController.switchContent("/org/trainer/interval_trainer/create-routine-view.fxml");
    }

    private void populateMyRoutinesBox() {
        try {
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM routines WHERE created_by=? LIMIT 10")) {
                statement.setString(1, currentUser.getEmail());
                try (ResultSet rs = statement.executeQuery()) {
                    int row = 1;
                    while (rs.next()) {
                        String routineName = rs.getString("name");
                        Timestamp createdOn = rs.getTimestamp("created_on");
                        String description = rs.getString("description");
                        int totalTime = rs.getInt("total_time");

                        // Convert Timestamp to LocalDateTime
                        LocalDateTime createdDateTime = createdOn.toLocalDateTime();

                        // Format LocalDateTime according to your desired format
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String formattedCreatedOn = createdDateTime.format(formatter);

                        // Display routine data in the grid
                        myRoutinesGrid.add(new Label(routineName), 0, row);
                        myRoutinesGrid.add(new Label(formattedCreatedOn), 1, row);
                        myRoutinesGrid.add(new Label(description), 2, row);
                        myRoutinesGrid.add(new Label(String.valueOf(totalTime)), 3, row);
                        row++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
