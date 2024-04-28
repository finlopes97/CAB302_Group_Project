package org.trainer.interval_trainer.Activity;

import javafx.fxml.FXML;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;


public class ActivityController {

    @FXML
    protected void onArrowButtonClick() throws IOException {
        HelloApplication.changeScene("new-routine-view");
    }

    @FXML
    protected void onPauseButtonClick() {
    //Pause Timer
    }
    @FXML
    protected void onStartButtonClick() throws IOException {
    HelloApplication.changeScene("activity-start-view");
    }

}
