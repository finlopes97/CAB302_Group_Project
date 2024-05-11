package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.User;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class MyRoutinesController {

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
    @FXML
    private Button createRoutineButton;
    private User currentUser1;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    @FXML
    protected void initialize() {
        // Print currentUser when the controller is initialized
    }

    // Method to set the current user
    public void setCurrentUser(User user) {
        this.currentUser1 = user;
        // Debug print to confirm the value of currentUser when it's set
        System.out.println("****** MY ROUTINE - CurrentUser set ****** " + currentUser1);
    }


    @FXML
    protected void onCreateRoutinesClick() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/create-routine-view.fxml"));
            Parent root = loader.load();
            CreateRoutinesController controller = loader.getController();
            controller.setCurrentUser(currentUser1); // Pass the currentUser to the CreateRoutinesController
            System.out.println("****** MY ROUTINE ****** " + currentUser1);
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            Stage stage = (Stage) createRoutineButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
//            HelloApplication.changeScene("create-routine-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/create-routine-view.fxml"));
//            Parent root = loader.load();
//            CreateRoutinesController controller = loader.getController();
//            controller.setCurrentUser(currentUser); // Pass the currentUser to the CreateRoutinesController
////            Scene scene = new Scene(root);
////            Stage stage = (Stage) myRoutines.getScene().getWindow();
////            stage.setScene(scene);
////            stage.show();
//            System.out.println("****** MY ROUTINE ****** " + currentUser);
//            HelloApplication.changeScene("create-routine-view.fxml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}