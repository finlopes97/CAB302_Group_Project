package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.Model.Session;
import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.controller.manage_routines.RoutineListController;
import org.trainer.interval_trainer.controller.manage_routines.RoutinePreviewController;

import java.util.List;
import java.util.Optional;

/**
 * Controller for the Home view of the application, handling the display
 * of both user-specific and suggested routines.
 */
public class HomeController extends RoutineListController {

    @FXML private VBox children2;
    @FXML private VBox children;
    @FXML private Label welcomeText;
    @FXML private GridPane myRoutinesGrid;
    @FXML private GridPane suggestedRoutinesGrid;
    @FXML private GridPane suggestedRoutinesGrid2;
    private Session session;
    private User currentUser;

    /**
     * Initializes the controller, populates the grids with routines, and sets the welcome message.
     */
    @FXML
    protected void initialize() {
        session = Session.getInstance();
        currentUser = session.getCurrentUser();

        updateView(); // Populate routines grid
        welcomeText.setText("Welcome, " + currentUser.getName());
    }

    @Override
    public void updateView() {
        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
        List<Routine> routines = dao.getSomeRoutines(Optional.ofNullable(currentUser.getName()), 5);
        System.out.println(routines);
        children.getChildren().clear();
        for (Routine routine : routines) {
            children.getChildren().add(new RoutinePreviewController(routine, this));
        }

        //suggested routines

        List<Routine> routines2 = dao.getSomeRoutinesNotByUser(Optional.ofNullable(currentUser.getName()), 5);
        System.out.println(routines2);
        children2.getChildren().clear();
        for (Routine routine : routines2) {
            children2.getChildren().add(new RoutinePreviewController(routine, this));
        }


    }
}