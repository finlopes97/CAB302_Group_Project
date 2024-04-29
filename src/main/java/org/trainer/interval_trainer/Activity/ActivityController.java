package org.trainer.interval_trainer.Activity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.NewRoutine.Interval;
import java.io.IOException;
import javafx.scene.control.Label;



public class ActivityController {

    @FXML
    protected void onArrowButtonClick() throws IOException {
        HelloApplication.changeScene("new-routine-view.fxml");
    }

    @FXML
    protected void onPauseButtonClick() {
    if (timeline != null) {
        timeline.stop();
    }
    }
    private Timeline timeline;
    private static Integer seconds = 30;
    @FXML
    private Label timerLabel;
    @FXML
    protected void timer() {

        timerLabel.setText(seconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            seconds--;
                            timerLabel.setText(seconds.toString());
                            if (seconds <= 0) {
                                timeline.stop();
                            }
                        })
        );
        timeline.playFromStart();

    }
    @FXML
    protected void onStartButtonClick() throws IOException {
        HelloApplication.changeScene("activity-start-view.fxml");
        timer();
    }




}
