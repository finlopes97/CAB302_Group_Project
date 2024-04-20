package org.trainer.interval_trainer.NewRoutine;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Group extends Block  {
    @FXML private Button addchild;
    @FXML private Button addItem;
    @FXML private Button delete;
    @FXML private VBox children;


    ObservableList<Node> list = FXCollections.<Node>observableArrayList();


    public Group(Block par) {
        super(par);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }





        Bindings.bindContentBidirectional(list, children.getChildren());

        addchild.setOnAction(event -> {
            list.add(new Group(this));
        });
        addItem.setOnAction(event -> {
            list.add(new Item(this));
        });
        delete.setOnAction(event -> {
            Delete();
        });
    }

    @Override
    public void addAfterChild(Block child, Block newBlock) {
        list.add(list.indexOf(child) +1, newBlock);

    }


    @Override
    public void deleteChild(Node child) {
        list.remove(child);
    }
}
