package org.trainer.interval_trainer.controller.new_routine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.BaseItem;
import org.trainer.interval_trainer.Model.Block;

import java.io.IOException;

import static org.trainer.interval_trainer.controller.new_routine.RoutineController.popup;

/**
 * Controller for individual "Block" elements in the application.
 * Handles user interactions and visual representation of a Block.
 */
public class BlockController extends BaseController {
    @FXML private Button openPopup;
    @FXML private TextField name;

    private final Block data;

    /**
     * Retrieves the Block data associated with this controller.
     * @return data Block data to be managed by this controller.
     */
    @Override
    public BaseItem getData() {
        return data;
    }

    /**
     * Constructor initializes the controller with a specific Block.
     * @param data Block data to be managed by this controller.
     */
    public BlockController(Block data) {
        this.data = data;
        FXMLLoader blockLoader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/new_routine/item-view.fxml"));
        blockLoader.setController(this);
        blockLoader.setRoot(this);
        try {
            blockLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load block item view.", e);
        }

        setupBindings();
        setupDragAndDrop();
        updateView();
    }

    /**
     * Sets up data bindings and listeners for the Block data.
     */
    private void setupBindings() {
        name.textProperty().bindBidirectional(data.getName());
        data.getTimeInSeconds().addListener((observable, oldValue, newValue) -> updateView());
    }

    /**
     * Configures drag and drop behavior for the Block.
     */
    private void setupDragAndDrop() {
        setOnDragDetected(event -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("block_drag"); // Placeholder value
            db.setContent(content);
            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        setOnDragDropped(event -> {
            boolean success = false;
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                handleDragDropped(event);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    /**
     * Handles drag-and-drop operations, rearranging blocks within the parent.
     * @param event The drag event containing drag information.
     */
    private void handleDragDropped(DragEvent event) {
        BaseItem sourceItem = ((BaseController) event.getGestureSource()).getData();
        sourceItem.getParent().getChildren().remove(sourceItem);
        data.getParent().getChildren().add(data.getParent().getChildren().indexOf(data) + 1, sourceItem);
        sourceItem.setParent(data.getParent());
    }


    /**
     * Updates the view to reflect the current state of the block data, such as timing.
     */
    private void updateView() {
        int timeInSec = data.getTimeInSeconds().get();
        int mins = timeInSec / 60;
        int secs = timeInSec % 60;

        openPopup.setText(String.format("%02d:%02d", mins, secs));
    }

    /**
     * Deletes the block from its parent container.
     * @param event The action event triggered by the UI.
     */
    public void deleteBlock(ActionEvent event) {
        data.getParent().getChildren().remove(data);
    }

    /**
     * Opens a popup for editing block details.
     * @param event The action event triggered by the UI.
     */
    public void openPopup(ActionEvent event) {
        if (!popup.isShowing()) {
            popup.setBlock(data);
            popup.show(HelloApplication.getPrimaryStage());
        }
    }
}