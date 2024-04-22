package org.trainer.interval_trainer.NewRoutine;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;

import java.io.IOException;

public class Item extends ChildBlock {
    @FXML private Button openPopup;


    public Item(Block par) {
        super(par, "/org/trainer/interval_trainer/new_routine/item-view.fxml");

        TimerPopup timer = new TimerPopup(openPopup);



        openPopup.setOnAction(event -> {
            timer.toggleShow();
        });



        setOnDragDropped(new EventHandler<>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = db.hasString();

                ((Block) event.getGestureSource()).Delete();
                ((Block) event.getGestureSource()).parent = parent;
                AddAfter((Block) event.getGestureSource());
                event.setDropCompleted(success);
                event.consume();
            }
        });

    }
}
