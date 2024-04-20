package org.trainer.interval_trainer.NewRoutine;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Routine extends Block {


    @FXML private VBox children;
    @FXML private Button addChild;
    @FXML private Button addItem;
    @FXML private TextField routineName = new TextField();
    @FXML private Button save;

    ObservableList<Node> list = FXCollections.<Node>observableArrayList();

    public Routine() {
        super(null);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/routine-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

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
