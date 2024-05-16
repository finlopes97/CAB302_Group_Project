package org.trainer.interval_trainer.controller.activity;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.trainer.interval_trainer.HelloApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import org.trainer.interval_trainer.Model.BaseItem;
import org.trainer.interval_trainer.Model.Block;
import org.trainer.interval_trainer.Model.Group;
import org.trainer.interval_trainer.Model.Routine;
import org.trainer.interval_trainer.controller.MainController;


public class ActivityController {

    @FXML
    public Label name;
    public VBox children;

    @FXML Label debug;
    @FXML Label timerLabel;

    private Timeline timeline;

    private static Integer seconds = 30;

    private Routine routine;

    public List<Integer> currentIndex = new ArrayList<>();

    public void setRoutine(Routine routine) {
        this.routine = routine;
        currentIndex.add(0);
        while (getCurrentItem() instanceof Group) {
            currentIndex.add(0);
        }

        System.out.println(getCurrentItem());
        setUpNewItem();
    }


    public BaseItem getCurrentItem() {
        Group group = routine.getGroup();
        BaseItem item = group;

        for (Integer index : currentIndex) {
            if (item.getParent() == null) {
                item = group.getChildren().get(index);
            } else {
                System.out.println(item);
                int realIndex = (index % ((Group)item).getChildren().size() );
                System.out.println(currentIndex);
                item = ((Group) item).getChildren().get(realIndex);
            }
        }

        return item;
    }



    @FXML
    protected void onArrowButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/main-view.fxml"));
        HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
        ((MainController) loader.getController()).switchContent("/org/trainer/interval_trainer/manage_routines/my-routine-view.fxml");
    }

    public void nextItem() {
        BaseItem item = getCurrentItem();

        int parentRealSize = item.getParent().getChildren().size() * item.getParent().getReps().get();

        if (currentIndex.getLast() + 1 >= parentRealSize) { //finished group
            if (item.getParent().getParent() == null) { // if is in base parent you've reached the end of the routine.
                System.out.println("done"); // we done finished the routine do logic or something
                onPauseButtonClick();
                debug.setText("done");
                System.out.println(getCurrentItem());
                return;
            }
            currentIndex.removeLast();
            nextItem();
            return;
        }
        int index = currentIndex.getLast();
        currentIndex.removeLast();
        currentIndex.add(index + 1);
        while (getCurrentItem() instanceof Group) {
            currentIndex.add(0);
        }
        // setup timer
        //next block logic
    }

    public void prevItem() {
        BaseItem item = getCurrentItem();
        if (currentIndex.getLast() - 1 < 0) {
            //start of group
            if (item.getParent().getParent() == null) {
                System.out.println("start of list");
                onPauseButtonClick();
                return;
            }
            currentIndex.removeLast();
            prevItem();
            return;
        }
        int index = currentIndex.getLast();
        currentIndex.removeLast();
        currentIndex.add(index -1);
        while (getCurrentItem() instanceof Group) {
            currentIndex.add((((Group) getCurrentItem()).getChildren().size()) * ((Group) getCurrentItem()).getReps().get() -1);
        }
    }

    public void setUpNewItem() {
        if (getCurrentItem() instanceof Block) {
            Block block = (Block) getCurrentItem();
            seconds = ((Block) getCurrentItem()).getTimeinSeconds().get();

            name.setText(block.getName().get());

            children.getChildren().clear();
            System.out.println("___________________________-");
            visualize(routine.getGroup(), 0);
        }

    }

    public void visualize(Group group, int depth) {
        int idx;
        for (idx = 0; idx < group.getChildren().size(); idx ++) {
            BaseItem item = group.getChildren().get(idx);
            if (item instanceof Group) {
                children.getChildren().add(new Label(((Group) item).getName().get() + ":"));
                visualize((Group) item, depth+1);


            }
            else {
                String name = ((Block) item).getName().get();
                Label label = new Label("-------------".substring(13-depth) + name);

                children.getChildren().add(label);


                if (getCurrentItem() == (Block) item) {
                    label.setUnderline(true);
                }
            }

        }

    }





    @FXML
    protected void timer() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            seconds--;
                            if (seconds <= 0 ) {
                                nextItem();
                            }

                            timerLabel.setText(String.valueOf(seconds / 60) + String.valueOf(seconds % 60));
                        })
        );
        timeline.playFromStart();
    }


    public void onPauseButtonClick() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void onNext(ActionEvent event) {
        nextItem();
        setUpNewItem();
    }
    public void onPrev(ActionEvent event) {
        prevItem();
        setUpNewItem();
    }

    public void onStart(ActionEvent event) {
        if (timeline == null) {
            timer();
            return;
        }
        if (timeline.getStatus() != Animation.Status.RUNNING) {
            timer();
        }
    }
}
