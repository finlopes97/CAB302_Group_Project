package org.trainer.interval_trainer.controller.manage_routines;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.controller.new_routine.RoutineController;

import java.io.IOException;

public class RoutinePreviewController extends VBox {

    @FXML Label name;
    @FXML Label description;

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
    }

    public void delete(ActionEvent event) {

    }
    public void play(ActionEvent event) {

    }
    public void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new_routine/routine-view.fxml"));
        HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
        ((RoutineController) loader.getController()).setRoutine(data);
        

    }


}
