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

/**
 * Controls the search functionality within the application.
 * It manages search operations and updates the UI with search results and suggested routines.
 */
public class SearchController extends RoutineListController {
    @FXML private VBox children;
    @FXML private Label welcomeText;
    @FXML private Label header;
    @FXML private Button homeButton;
    @FXML private Button createRoutinesButton;
    @FXML private Button searchPageButton;
    @FXML private Button searchButton;
    @FXML private GridPane suggestedRoutinesGrid;
    @FXML private Label suggestedRoutineLabel;
    @FXML private Label searchResultLabel;
    @FXML private TextField searchField;

    private String search;

    /**
     * Updates the display of routines based on the current search query.
     * It queries the database for routines that match the search criteria and displays them.
     */
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

    /**
     * Handles the action triggered by clicking the search button.
     * It sets the search query based on the text field input and updates the view to display the results.
     * @throws IOException if an input/output error occurs.
     */
    @FXML
    protected void onSearchButtonClick() throws IOException {
        search = searchField.getText();
        updateView();
    }
}