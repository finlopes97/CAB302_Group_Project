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
import org.trainer.interval_trainer.Model.Session;
import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
import org.trainer.interval_trainer.controller.new_routine.RoutineController;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MyRoutinesController extends RoutineListController {

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
    @FXML private Button createRoutineButton;
    @FXML VBox routinesVBox;

    @FXML
    protected void initialize() {
        updateView();
    }


    @FXML
    protected void onCreateRoutinesClick() throws IOException {
        HelloApplication.changeScene("new_routine/routine-view.fxml");
    }

    @Override
    public void updateView() {
        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
        List<Routine> routines = dao.getAllRoutines(Optional.ofNullable(Session.getInstance().getCurrentUser().getName()));
        System.out.println(routines);

        routinesVBox.getChildren().clear();

        for (Routine routine : routines) {
            routinesVBox.getChildren().add(new RoutinePreviewController(routine, this));
        }
    }
}