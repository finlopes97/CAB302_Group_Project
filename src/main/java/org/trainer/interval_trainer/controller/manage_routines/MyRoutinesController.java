package org.trainer.interval_trainer.controller.manage_routines;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
import org.trainer.interval_trainer.controller.new_routine.RoutineController;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for managing the "My Routines" view in the application.
 * This controller handles the display and interaction with the list of existing training routines.
 */
public class MyRoutinesController {

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
    @FXML private Button createRoutineButton;
    @FXML VBox routinesVBox;

    /**
     * Initializes the controller by loading all routines from the database and displaying
     * them in the VBox container. Each routine is represented by a RoutinePreviewController.
     */
    @FXML
    protected void initialize() {
        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
        List<Routine> routines = dao.getAllRoutines();
        System.out.println(routines);

        for (Routine routine : routines) {
            routinesVBox.getChildren().add(new RoutinePreviewController(routine));
        }
    }

    /**
     * Handles the event when the 'Create Routine' button is clicked.
     * Changes the scene to the view where a new routine can be created.
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    @FXML
    protected void onCreateRoutinesClick() throws IOException {
        HelloApplication.changeScene("new_routine/routine-view.fxml");
    }

}