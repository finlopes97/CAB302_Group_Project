package org.trainer.interval_trainer.NewRoutine;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

public class Group extends ChildBlock  {
    @FXML private Button addchild;
    @FXML private Button addItem;

    @FXML private VBox children;


    ObservableList<Node> list = FXCollections.<Node>observableArrayList();


    public Group(Block par) {
        super(par, "/org/trainer/interval_trainer/new_routine/group-view.fxml");


        Bindings.bindContentBidirectional(list, children.getChildren());

        addchild.setOnAction(event -> {
            list.add(new Group(this));
        });
        addItem.setOnAction(event -> {
            list.add(new Item(this));
        });


        Group me = this;

        setOnDragDropped(new EventHandler<DragEvent>() {
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


    }

    @Override
    public void addAfterChild(Block child, Block newBlock) {
        list.add(list.indexOf(child) +1, newBlock);

    }


    @Override
    public void deleteChild(Node child) {
        list.remove(child);
        System.out.println("list: ");
        System.out.println(list);
    }
}
