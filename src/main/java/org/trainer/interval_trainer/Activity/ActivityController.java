package org.trainer.interval_trainer.Activity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import org.trainer.interval_trainer.HelloApplication;
import java.io.IOException;
import javafx.scene.control.Label;



public class ActivityController {

    @FXML
    protected void onArrowButtonClick() throws IOException {
        HelloApplication.changeScene("main-view.fxml");
    }

    @FXML
    protected void onPauseButtonClick() {
    if (timeline != null) {
        timeline.stop();
    }
    }
    private Timeline timeline;
    //change later
    private static Integer minutes = 1;
    private static Integer seconds = 30;
    @FXML
    private Label timerLabel;
    @FXML
    protected void timer() {
        timerLabel.setText(minutes.toString()+":"+seconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            seconds--;
                            if (minutes <= 0 && seconds <= 0) {
                                timeline.stop();
                            }
                            else if(seconds <0){
                                minutes--;
                                seconds=59;
                            }
                            timerLabel.setText(minutes.toString()+":"+seconds.toString());
                        })
        );
        timeline.playFromStart();

    }
    @FXML
    protected void onStartButtonClick() throws IOException {
        timer();
    }




}
