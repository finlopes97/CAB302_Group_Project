package org.trainer.interval_trainer.controller.new_routine;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.BaseItem;
import org.trainer.interval_trainer.Model.Block;
import org.trainer.interval_trainer.Model.Group;

public class RoutinesController {

    @FXML public VBox startOfList;
    @FXML public VBox endOfList;
    @FXML private VBox children;

    @FXML private TextField name;
    @FXML private TextField reps;
    @FXML private Button openPopup;




    private final Group data = new Group();

    public static PopupController popup;


    @FXML
    public void initialize() {
        popup = new PopupController(this);
        updateView();

        data.getChildren().addListener(new ListChangeListener<BaseItem>() {
            @Override
            public void onChanged(Change<? extends BaseItem> change) {
                updateView();
            }
        });


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
                BaseItem firstBlock = ((BaseController) event.getGestureSource()).getData();
                firstBlock.getParent().getChildren().remove(firstBlock);

                firstBlock.setParent(data);
                data.getChildren().addFirst(firstBlock);

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

                BaseItem firstBlock = ((BaseController) event.getGestureSource()).getData();
                firstBlock.getParent().getChildren().remove(firstBlock);

                firstBlock.setParent(data);
                data.getChildren().add(firstBlock);

                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    /**
     * recursive function to update the view
     */
    private void updateView () {
        children.getChildren().clear();
        for (BaseItem child : data.getChildren()) {
            if (child instanceof Block) {
                children.getChildren().add(new BlockController((Block) child));

            } else if (child instanceof Group) {
                children.getChildren().add(new GroupController((Group) child));
            }
        }
    }




    /**
     * will add a new block to group variable
     * called from add button on routine
     */
    public void addBlock(ActionEvent actionEvent) {
        Block newBlock = new Block();
        newBlock.setParent(data);
        data.getChildren().add(newBlock);

    }

    /**
     * will add a new block to group variable
     * called from add button on routine
     *
     */
    public void addGroup(ActionEvent actionEvent) {
        Group newGroup = new Group();
        newGroup.setParent(data);
        data.getChildren().add(newGroup);
    }

}

