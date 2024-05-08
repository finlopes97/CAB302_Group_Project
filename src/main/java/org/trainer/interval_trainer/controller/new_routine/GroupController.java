package org.trainer.interval_trainer.controller.new_routine;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import org.trainer.interval_trainer.Model.BaseItem;
import org.trainer.interval_trainer.Model.Block;
import org.trainer.interval_trainer.Model.Group;

import java.io.IOException;
import java.util.regex.Pattern;

public class GroupController extends BaseController {

    @FXML
    private VBox children;
    @FXML
    private TextField name;
    @FXML
    private TextField reps;

    private Group data;

    public Group getData() {
        return data;
    }

    public GroupController(Group data) {
        this.data = data;
        FXMLLoader groupLoader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/group-view.fxml"));

        groupLoader.setController(this);
        groupLoader.setRoot(this);
        try {
            groupLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        name.textProperty().bindBidirectional(data.getName());
        TextFormatter<Integer> formatter = new TextFormatter<>(
                new IntegerStringConverter(),
                0,
                c -> Pattern.matches("\\d*", c.getText()) ? c : null);
        formatter.valueProperty().bindBidirectional(data.getRepsObject());
        reps.setTextFormatter(formatter);

        setOnDragDetected((MouseEvent event) -> {
            Dragboard db = ((Node) event.getSource()).startDragAndDrop(TransferMode.MOVE);

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

        setOnDragDropped(new EventHandler<>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    System.out.println(db.getString());
                    success = true;
                }

                BaseItem firstBlock = ((BaseController) event.getGestureSource()).getData();
                firstBlock.getParent().getChildren().remove(firstBlock);

                data.getChildren().addFirst(firstBlock);
                firstBlock.setParent(data);

                event.setDropCompleted(success);
                event.consume();
            }
        });

        data.getChildren().addListener(new ListChangeListener<BaseItem>() {
            @Override
            public void onChanged(Change<? extends BaseItem> change) {
                System.out.println("updated");
                updateView();
            }
        });
    }


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

    public void deleteGroup(ActionEvent event) {
        data.getParent().getChildren().remove(data);
    }

    public void addChildGroup(ActionEvent event) {
        Group newGroup = new Group();
        newGroup.setParent(data);
        data.getChildren().add(newGroup);
    }

    public void addChildBlock(ActionEvent event) {
        Block newBlock = new Block();
        newBlock.setParent(data);
        data.getChildren().add(newBlock);
    }
}
