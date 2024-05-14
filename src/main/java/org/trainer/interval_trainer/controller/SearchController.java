package org.trainer.interval_trainer.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
import org.trainer.interval_trainer.controller.manage_routines.RoutineListController;
import org.trainer.interval_trainer.controller.manage_routines.RoutinePreviewController;



import java.io.IOException;
import java.util.List;


public class SearchController extends RoutineListController {
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

    private String search;


    @Override
    public void updateView() {
        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
        List<Routine> routines = dao.searchRoutine(search);
        System.out.println(routines);
        children.getChildren().clear();
        for (Routine routine : routines) {
            children.getChildren().add(new RoutinePreviewController(routine, this));
        }

    }

    @FXML
    protected void onSearchButtonClick() throws IOException {
        search = searchField.getText();
        updateView();
    }
}