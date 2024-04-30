package org.trainer.interval_trainer.controller.new_routine;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.Model.BaseItem;
import org.trainer.interval_trainer.Model.Block;
import org.trainer.interval_trainer.Model.Group;

import java.io.IOException;
import java.util.Iterator;

public class RoutinesController {

    @FXML private VBox children;

    @FXML private TextField name;




    private final Group group1 = new Group();

    public RoutinesController() {

    }

    /**
     * recursive function to update the view
     */
    void updateGroup (Group group) {
        children.getChildren().clear();
        for (BaseItem child : group.getChildren()) {
            if (child instanceof Block) {
                FXMLLoader blockLoader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/item-view.fxml"));
                blockLoader.setController(this);
                try {
                    Node node = blockLoader.load();
                    node.setUserData(child);
                    name.textProperty().bindBidirectional(((Block) child).getName());

                    node.setOnDragDetected((MouseEvent event) -> {
                        Dragboard db = ((Node) event.getSource()).startDragAndDrop(TransferMode.MOVE);

                        ClipboardContent content = new ClipboardContent();
                        content.putString("obama");
                        db.setContent(content);
                        event.consume();
                    });

                    node.setOnDragOver(new EventHandler<>() {
                        @Override
                        public void handle(DragEvent event) {
                            if (event.getGestureSource() != node && event.getDragboard().hasString()) {
                                event.acceptTransferModes(TransferMode.MOVE);
                            }
                            event.consume();
                        }
                    });
                    node.setOnDragDropped(new EventHandler<>() {
                        @Override
                        public void handle(DragEvent event) {
                            Dragboard db = event.getDragboard();
                            boolean success = false;
                            if (db.hasString()) {
                                System.out.println(db.getString());
                                success = true;
                            }

                            BaseItem firstBlock = (BaseItem) ((Node) event.getGestureSource()).getUserData();
                            firstBlock.getParent().getChildren().remove(firstBlock);

                            BaseItem droppedOn = (BaseItem) ((Node) event.getGestureTarget()).getUserData();
                            ObservableList<BaseItem> children1 = droppedOn.getParent().getChildren();
                            children1.add(children1.indexOf(droppedOn)+1, firstBlock);
                            firstBlock.setParent(droppedOn.getParent());


                            updateGroup(group1);

                            event.setDropCompleted(success);
                            event.consume();

                        }
                    });

                    children.getChildren().add(node);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                /* -------------------------------------------------------------*/
            } else if (child instanceof Group) {
                VBox temp = children;
                FXMLLoader groupLoader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/group-view.fxml"));
                groupLoader.setController(this);
                try {
                    Node node = groupLoader.load();
                    node.setUserData(child);

                    node.setOnDragDetected((MouseEvent event) -> {
                        Dragboard db = ((Node) event.getSource()).startDragAndDrop(TransferMode.MOVE);

                        ClipboardContent content = new ClipboardContent();
                        content.putString("obama");
                        db.setContent(content);
                        event.consume();
                    });

                    node.setOnDragOver(new EventHandler<>() {
                        @Override
                        public void handle(DragEvent event) {
                            if (event.getGestureSource() != node && event.getDragboard().hasString()) {
                                event.acceptTransferModes(TransferMode.MOVE);
                            }
                            event.consume();
                        }
                    });

                    node.setOnDragDropped(new EventHandler<>() {
                        @Override
                        public void handle(DragEvent event) {
                            Dragboard db = event.getDragboard();
                            boolean success = false;
                            if (db.hasString()) {
                                System.out.println(db.getString());
                                success = true;
                            }

                            BaseItem firstBlock = (BaseItem) ((Node) event.getGestureSource()).getUserData();
                            firstBlock.getParent().getChildren().remove(firstBlock);

                            Group droppedOn = (Group) ((Node) event.getGestureTarget()).getUserData();
                            droppedOn.getChildren().add(firstBlock);
                            firstBlock.setParent(droppedOn);


                            event.setDropCompleted(success);
                            event.consume();
                            updateGroup(group1);
                        }
                    });


                    temp.getChildren().add(node);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                updateGroup((Group) child);
                children = temp;
            }
        }
    }

    /**
     * will add a new block to group variable
     * called from add button on routine
     */
    public void addBlock(ActionEvent actionEvent) {
        Block newBlock = new Block();
        newBlock.setParent(group1);
        group1.getChildren().add(newBlock);
        updateGroup(group1);
    }

    /**
     * will add a new block to group variable
     * called from add button on routine
     *
     */
    public void addGroup(ActionEvent actionEvent) {
        Group newGroup = new Group();
        newGroup.setParent(group1);
        group1.getChildren().add(newGroup);
        updateGroup(group1);
    }


    /**
     * will add a group to a group
     * called from  add group button on a group
     *
     */
    public void addChildGroup(ActionEvent event) {
        Group bruh = (Group) ((Node) event.getSource()).getParent().getParent().getUserData();
        Group newGroup = new Group();
        newGroup.setParent(bruh);
        bruh.getChildren().add(newGroup);
        updateGroup(group1);
    }

    /**
     * will add a block to a group
     * called from add block button on a group
     */
    public void  addChildBlock(ActionEvent event) {
        Group bruh = (Group) ((Node) event.getSource()).getParent().getParent().getUserData();
        Block newBlock = new Block();
        newBlock.setParent(bruh);
        bruh.getChildren().add(newBlock);
        updateGroup(group1);
    }

    /**
     * will delete a group from a group or routine
     * called from groups;
     */
    public void deleteGroup(ActionEvent event) {
        Group bruh = (Group) ((Node) event.getSource()).getParent().getParent().getUserData();
        ((Group) bruh.getParent()).getChildren().remove(bruh);
        updateGroup(group1);
    }
    public void deleteBlock(ActionEvent event) {
        Block bruh = (Block) ((Node) event.getSource()).getParent().getParent().getUserData();
        ((Group) bruh.getParent()).getChildren().remove(bruh);
        updateGroup(group1);
    }
}

