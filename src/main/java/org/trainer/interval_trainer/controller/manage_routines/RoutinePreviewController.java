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

public class RoutinePreviewController extends VBox {

    @FXML Label name;
    @FXML Label description;
    @FXML VBox children;
    @FXML
    Button delete;
    @FXML Button edit;

    private Routine data;
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

        if (!Objects.equals(Session.getInstance().getCurrentUser().getName(), routine.getName().get())) {
            getChildren().remove(delete);
            getChildren().remove(edit);
        }

    }

    public void delete(ActionEvent event) {
        new SqliteRoutinesDAO().deleteRoutine(data);
        try {
            HelloApplication.changeScene("/org/trainer/interval_trainer/manage_routines/my-routine-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void play(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/activity/activity-page-view.fxml"));
        HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
        ((ActivityController) loader.getController()).setRoutine(data);

    }
    public void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/routine-view.fxml"));
        HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
        ((RoutineController) loader.getController()).setRoutine(data);
    }
}
