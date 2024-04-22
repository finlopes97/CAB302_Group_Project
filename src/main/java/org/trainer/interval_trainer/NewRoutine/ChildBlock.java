package org.trainer.interval_trainer.NewRoutine;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.*;

public class ChildBlock extends Block{
    @FXML private Button delete;
    public ChildBlock(Block par, String path) {
        super(par, path);

        delete.setOnAction(event -> parent.deleteChild(this));

        setOnDragDetected((MouseEvent event) -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString("obama");
            db.setContent(content);
            event.consume();
        });
        setOnDragOver(new EventHandler<>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
    }
}
