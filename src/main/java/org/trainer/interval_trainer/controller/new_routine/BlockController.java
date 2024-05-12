package org.trainer.interval_trainer.controller.new_routine;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.BaseItem;
import org.trainer.interval_trainer.Model.Block;

import java.io.IOException;

import static org.trainer.interval_trainer.controller.new_routine.RoutineController.popup;

public class BlockController extends BaseController {
    @FXML private Button openPopup;
    @FXML private TextField name;


    private Block data;
    @Override
    public BaseItem getData() {
        return data;
    }

    public BlockController(Block data) {
        this.data = data;
        FXMLLoader blockLoader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/item-view.fxml"));
        blockLoader.setController(this);
        blockLoader.setRoot(this);
        try {
            blockLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        updateView();

        data.getTimeinSeconds().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                updateView();
            }
        });

        name.textProperty().bindBidirectional(data.getName());

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

                data.getParent().getChildren().add(data.getParent().getChildren().indexOf(data)+1, firstBlock);
                firstBlock.setParent(data.getParent());

                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    private void updateView() {
        int timeinsec = data.getTimeinSeconds().get();
        int mins = timeinsec / 60;
        int secs = timeinsec % 60;

        openPopup.setText(String.format("%02d:%02d", mins, secs));
    }

    public void deleteBlock(ActionEvent event) {
        data.getParent().getChildren().remove(data);
    }

    public void openPopup(ActionEvent event) {
        if (!popup.isShowing()) {
            popup.setBlock(data);
            popup.show(HelloApplication.getPrimaryStage());
        }
    }


}
