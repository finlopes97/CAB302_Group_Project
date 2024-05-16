package org.trainer.interval_trainer.controller.manage_routines;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.controller.activity.ActivityController;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.*;
import org.trainer.interval_trainer.controller.new_routine.RoutineController;


import java.io.IOException;
import java.util.Objects;

/**
 * A controller for the preview of a single routine, used in lists of routines.
 * This controller provides a visual component for interacting with an individual routine.
 */
public class RoutinePreviewController extends VBox {
    @FXML Label name;
    @FXML Label description;
    @FXML VBox children;
    @FXML Button delete;
    @FXML Button edit;

    private final Routine data;
    private final RoutineListController controller;

    /**
     * Constructs a RoutinePreviewController with a specific Routine.
     * It loads the associated FXML and initializes the display labels with the routine's details.
     * @param routine The Routine to be displayed and managed by this controller.
     * @param controller The list controller that manages updates and interactions with the list of routines.
     */
    public RoutinePreviewController(Routine routine, RoutineListController controller) {
        this.data = routine;
        this.controller = controller;

        loadFXML();
        setupUI(routine);
    }

    /**
     * Loads the FXML file associated with this controller.
     * This method sets the controller and root for the FXMLLoader before loading.
     */
    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/manage_routines/routine_preview.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + e.getMessage());
        }
    }

    /**
     * Sets up the UI elements with data from the routine.
     * It initializes labels with the routine's name and description, and hides edit and delete buttons based on user permissions.
     * @param routine The Routine whose data is to be displayed.
     */
    private void setupUI(Routine routine) {
        name.setText(data.getName().get());
        description.setText(data.getDescription().get());

        // Set the visibility of edit and delete buttons based on whether the current user is the creator of the routine
        if (!Objects.equals(Session.getInstance().getCurrentUser().getName(), routine.getCreatedBy())) {
            delete.setVisible(false);
            edit.setVisible(false);
        }
    }

    /**
     * Deletes the routine associated with this controller from the database,
     * and refreshes the UI to reflect this change.
     * @param event The event that triggered this method.
     */
    public void delete(ActionEvent event) {
        new SqliteRoutinesDAO().deleteRoutine(data);
        controller.updateView();
    }
    /**
     * Initiates the playing or execution of the routine.
     * This method should handle the logic to start routine activities, such as timing or exercise displays.
     * @param event The event that triggered this method.
     */
    public void play(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/activity/activity-page-view.fxml"));
            HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
            ((ActivityController) loader.getController()).setRoutine(data);
        } catch (IOException e) {
            System.err.println("Error loading activity page: " + e.getMessage());
        }
    }
    /**
     * Opens the editing interface for the routine.
     * Allows the user to modify the details of the routine.
     * @param event The event that triggered this method.
     * @throws IOException If the FXML file for the edit view cannot be loaded.
     */
    @FXML
    private void edit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/routine-view.fxml"));
            HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
            ((RoutineController) loader.getController()).setRoutine(data);
        } catch (IOException e) {
            System.err.println("Error loading edit view: " + e.getMessage());
        }
    }
}
