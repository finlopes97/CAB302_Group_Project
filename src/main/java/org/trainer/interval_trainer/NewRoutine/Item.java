package org.trainer.interval_trainer.NewRoutine;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;

import java.io.IOException;

public class Item extends Block {
    @FXML
    private Button delete;


    public Item(Block par) {
        super(par);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/item-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        delete.setOnAction(event -> {
            Delete();
        });


        setOnDragDetected((MouseEvent event) -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString("obama");
            db.setContent(content);
            event.consume();
        });
        setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });

        setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    success = true;
                }


                ((Block) event.getGestureSource()).Delete();
                ((Block) event.getGestureSource()).parent = parent;
                AddAfter((Block) event.getGestureSource());
                event.setDropCompleted(success);
                event.consume();



            }
        });






    }
    @Override
    public void deleteChild(Node child) {

    }

    @Override
    public void addAfterChild(Block child, Block newBlock) {

    }
}
