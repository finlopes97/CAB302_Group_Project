package org.trainer.interval_trainer.controller.manage_routines;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.*;
import org.trainer.interval_trainer.controller.new_routine.BlockController;
import org.trainer.interval_trainer.controller.new_routine.GroupController;
import org.trainer.interval_trainer.controller.new_routine.RoutineController;


import java.io.IOException;

/**
 * A controller for the preview of a single routine, typically used in lists of routines.
 * This controller provides a visual component for interacting with an individual routine.
 */
public class RoutinePreviewController extends VBox {

    @FXML Label name;
    @FXML Label description;
    @FXML VBox children;
    private Routine data;

    /**
     * Constructs a RoutinePreviewController with a specific Routine.
     * It loads the associated FXML and initializes the display labels with the routine's details.
     * @param routine The Routine to be displayed and managed by this controller.
     */
    public RoutinePreviewController(Routine routine) {
        data = routine;

        FXMLLoader blockLoader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/manage_routines/routine_preview.fxml"));
        blockLoader.setController(this);
        blockLoader.setRoot(this);
        try {
            blockLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        name.setText(data.getName().get());
        description.setText(data.getDescription().get());
    }

    /**
     * Deletes the routine associated with this controller from the database,
     * and refreshes the UI to reflect this change.
     * @param event The event that triggered this method.
     */
    public void delete(ActionEvent event) {
        new SqliteRoutinesDAO().deleteRoutine(data);
        try {
            HelloApplication.changeScene("/org/trainer/interval_trainer/manage_routines/my-routine-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initiates the playing or execution of the routine.
     * This method should handle the logic to start routine activities, such as timing or exercise displays.
     * @param event The event that triggered this method.
     */
    public void play(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/activity-page-view.fxml"));
        HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
        ((RoutineController) loader.getController()).setRoutine(data);
    }

    /**
     * Opens the editing interface for the routine.
     * Allows the user to modify the details of the routine.
     * @param event The event that triggered this method.
     * @throws IOException If the FXML file for the edit view cannot be loaded.
     */
    public void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/routine-view.fxml"));
        HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
        ((RoutineController) loader.getController()).setRoutine(data);
    }
}
