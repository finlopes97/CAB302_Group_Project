package org.trainer.interval_trainer.Activity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import org.trainer.interval_trainer.HelloApplication;
import java.io.IOException;
import javafx.scene.control.Label;

import java.nio.ByteBuffer;
import java.sql.*;
import java.util.Arrays;


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
    private static Integer minutes = getInterval(1);
    private static Integer seconds = getInterval(1);
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

    public static Integer getInterval(Integer interval_time) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:./src/main/Database.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM routines WHERE data = ?");
            byte[] intervalBytes = ByteBuffer.allocate(4).putInt(interval_time).array();
            statement.setBytes(1, intervalBytes);
            System.out.println("interval_time: " + interval_time);
            System.out.println("intervalBytes: " + Arrays.toString(intervalBytes));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                byte[] dataBytes = resultSet.getBytes("data");
                System.out.println("dataBytes: " + Arrays.toString(dataBytes));
                ByteBuffer wrapped = ByteBuffer.wrap(dataBytes);
                interval_time = wrapped.getInt();
                System.out.println("Retrieved interval_time: " + interval_time);
                return interval_time;
            } else {
                System.out.println("No row found");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
