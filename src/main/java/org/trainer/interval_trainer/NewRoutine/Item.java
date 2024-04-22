package org.trainer.interval_trainer.NewRoutine;

import javafx.event.EventHandler;
import javafx.scene.input.*;

public class Item extends ChildBlock {

    public Item(Block par) {
        super(par, "/org/trainer/interval_trainer/new_routine/item-view.fxml");

        setOnDragDropped(new EventHandler<DragEvent>() {

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
