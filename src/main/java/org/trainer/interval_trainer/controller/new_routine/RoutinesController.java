package org.trainer.interval_trainer.controller.new_routine;

import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.util.converter.IntegerStringConverter;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.BaseItem;
import org.trainer.interval_trainer.Model.Block;
import org.trainer.interval_trainer.Model.Group;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

public class RoutinesController {

    @FXML public VBox startOfList;
    @FXML public VBox endOfList;
    @FXML private VBox children;

    @FXML private TextField name;
    @FXML private TextField reps;
    @FXML private Button openPopup;




    private final Group group1 = new Group();

    private final PopupController popup = new PopupController(this);



    public void Update() {
        updateGroup(group1);
    }

    @FXML
    public void initialize() {



        startOfList.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != startOfList && event.getDragboard().hasString()) {
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
                BaseItem firstBlock = (BaseItem) ((Node) event.getGestureSource()).getUserData();
                firstBlock.getParent().getChildren().remove(firstBlock);

                firstBlock.setParent(group1);

                group1.getChildren().addFirst(firstBlock);

                event.setDropCompleted(success);
                event.consume();
                updateGroup(group1);

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

                BaseItem firstBlock = (BaseItem) ((Node) event.getGestureSource()).getUserData();
                firstBlock.getParent().getChildren().remove(firstBlock);

                firstBlock.setParent(group1);

                group1.getChildren().add(firstBlock);
                event.setDropCompleted(success);
                event.consume();
                updateGroup(group1);

            }
        });
    }

    /**
     * recursive function to update the view
     */
    private void updateGroup (Group group) {
        children.getChildren().clear();
        for (BaseItem child : group.getChildren()) {
            if (child instanceof Block) {
                FXMLLoader blockLoader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/item-view.fxml"));
                blockLoader.setController(this);
                try {
                    Node node = blockLoader.load();
                    node.setUserData(child);

                    int timeinsec = ((Block) child).getTimeinSeconds().get();
                    int mins = timeinsec / 60;
                    int secs = timeinsec % 60;

                    openPopup.setText(String.format("%02d:%02d", mins, secs));
                    name.textProperty().bindBidirectional(((Block) child).getName());

                    setup(node);
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
                    name.textProperty().bindBidirectional(((Group) child).getName());

                    TextFormatter<Integer> formatter = new TextFormatter<>(
                            new IntegerStringConverter(),
                            0,
                            c -> Pattern.matches("\\d*", c.getText()) ? c : null );
                    formatter.valueProperty().bindBidirectional(((Group) child).getRepsObject());

                    reps.setTextFormatter(formatter);

                    setup(node);

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
                            droppedOn.getChildren().addFirst(firstBlock);
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

    private void setup(Node node) {
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


    public void openPopup(ActionEvent event) {
        if (!popup.isShowing()) {
            Block bruh = (Block) ((Node) event.getSource()).getParent().getParent().getUserData();
            popup.setBlock(bruh);

            popup.show(HelloApplication.getPrimaryStage());
        }


    }

}

