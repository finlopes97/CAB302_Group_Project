package org.trainer.interval_trainer.Activity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import org.trainer.interval_trainer.HelloApplication;
import java.io.IOException;
import javafx.scene.control.Label;

/**
 * Controller for managing the activity view in an interval training application.
 * This controller handles the timer operations and scene transitions within the application.
 */
public class ActivityController {

    private Timeline timeline;
    //change later
    private static Integer minutes = 1;
    private static Integer seconds = 30;
    @FXML
    private Label timerLabel;

    /**
     * Changes the scene to the create routine view by loading a specific FXML file.
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    @FXML
    protected void onArrowButtonClick() throws IOException {
        HelloApplication.changeScene("create-routine-view.fxml");
    }

    /**
     * Pauses the countdown of the timer if it is currently active.
     */
    @FXML
    protected void onPauseButtonClick() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Initializes and starts the countdown timer, updating the timer label every second.
     * Decrements the time by one second until reaching zero, at which point the timer stops.
     */
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

    /**
     * Changes the scene to the start activity view by loading a specific FXML file.
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    @FXML
    protected void onStartButtonClick() throws IOException {
        HelloApplication.changeScene("activity-start-view.fxml");
    }
}
