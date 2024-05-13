package org.trainer.interval_trainer.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import javafx.application.Platform;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
import org.trainer.interval_trainer.controller.manage_routines.RoutinePreviewController;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    public VBox children;
    @FXML
    private Label welcomeText;
    @FXML
    private Label header;
    @FXML
    private Button homeButton;
    @FXML
    private Button createRoutinesButton;
    @FXML
    private Button searchPageButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button settingsButton;
    @FXML
    private GridPane suggestedRoutinesGrid;

    @FXML
    private Label searchResultLabel;
    @FXML
    private TextField searchField;

    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";



    @FXML
    protected void onSearchButtonClick() throws IOException {
        System.out.println("Hello");
        String query = searchField.getText();
        System.out.println(query);
        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
        List<Routine> routines = dao.searchRoutine(query);
        System.out.println(routines);
        for (Routine routine : routines) {
            children.getChildren().add(new RoutinePreviewController(routine));
            
        }

    }
}