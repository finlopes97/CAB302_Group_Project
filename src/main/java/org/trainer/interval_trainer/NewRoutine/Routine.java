package org.trainer.interval_trainer.NewRoutine;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Routine extends Block {


    @FXML private VBox children;
    @FXML private Button addChild;
    @FXML private Button addItem;
    @FXML private TextField routineName = new TextField();
    @FXML private Button save;
    @FXML private VBox startOfList;
    @FXML private VBox endOfList;




    ObservableList<Node> list = FXCollections.<Node>observableArrayList();

    public Routine() {
        super(null, "/org/trainer/interval_trainer/new_routine/routine-view.fxml");


        Bindings.bindContentBidirectional(list, children.getChildren());

        addChild.setOnAction(event -> {
            list.add(new Group(this));
        });
        addItem.setOnAction(event -> {
            list.add(new Item(this));
        });
        save.setOnAction(event -> {
            System.out.println(routineName.getText());
        });

        Routine me = this;

        startOfList.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        startOfList.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    System.out.println(db.getString());
                    success = true;
                }
                System.out.println(event.getGestureSource());

                ((Block) event.getGestureSource()).Delete();
                ((Block) event.getGestureSource()).parent = me;


                list.addFirst((Block) event.getGestureSource());
                event.setDropCompleted(success);
                event.consume();

            }
        });
        endOfList.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        endOfList.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    System.out.println(db.getString());
                    success = true;
                }
                System.out.println(event.getGestureSource());

                ((Block) event.getGestureSource()).Delete();
                ((Block) event.getGestureSource()).parent = me;


                list.add((Block) event.getGestureSource());
                event.setDropCompleted(success);
                event.consume();

            }
        });



    }


    @Override
    public void addAfterChild(Block child, Block newBlock) {
        list.add(list.indexOf(child) + 1, newBlock);
    }

    @Override
    public void deleteChild(Node child) {
        list.remove(child);
    }
}
