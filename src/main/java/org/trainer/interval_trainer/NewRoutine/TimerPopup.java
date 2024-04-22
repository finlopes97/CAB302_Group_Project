package org.trainer.interval_trainer.NewRoutine;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableIntegerValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;

public class TimerPopup extends Popup {

    public Button closePopup;
    public Spinner mins;
    public Spinner secs;

    public Button openPo;

    public TimerPopup(Button openPopup) {
        openPo = openPopup;


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/popup-view.fxml"));
        try {
            VBox parent = fxmlLoader.load();
            getContent().add(parent);
            closePopup = (Button) parent.lookup("#closePopup");
            mins = (Spinner) parent.lookup("#mins");
            secs = (Spinner) parent.lookup("#secs");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fxmlLoader.setController(this);
        closePopup.setOnAction(actionEvent -> toggleShow());




    }

    public void toggleShow() {
        if (isShowing()) {
            hide();
        } else {
            show(HelloApplication.primaryStage);
        }
        openPo.setText(String.format("%02d:%02d", mins.getValueFactory().getValue(), secs.getValueFactory().getValue()));

    }


}
