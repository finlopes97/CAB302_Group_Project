package org.trainer.interval_trainer.controller.new_routine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Spinner;
import javafx.stage.Popup;
import org.trainer.interval_trainer.Model.Block;

import java.io.IOException;

public class PopupController extends Popup {

    @FXML
    private Spinner<Integer> secs;
    @FXML
    private Spinner<Integer> mins;

    private Block block;

    public void setBlock(Block block) {
        this.block = block;
        secs.getValueFactory().setValue(block.getTimeInSeconds().get()%60);
        mins.getValueFactory().setValue(block.getTimeInSeconds().get()/60);
    }

    private RoutineController routinesController;

    public PopupController(RoutineController routinesController) {
        this.routinesController = routinesController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/popup-view.fxml"));


        loader.setController(this);
        try {
            getContent().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closePopup() {
        block.getTimeInSeconds().set(((Integer) mins.getValueFactory().getValue()) * 60 + ((Integer) secs.getValueFactory().getValue()));
        hide();
    }

}
