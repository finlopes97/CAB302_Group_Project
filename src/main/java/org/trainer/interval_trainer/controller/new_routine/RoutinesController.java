//package org.trainer.interval_trainer.controller.new_routine;
//
//import javafx.collections.ListChangeListener;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.Node;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.input.*;
//import javafx.scene.layout.VBox;
//import org.trainer.interval_trainer.HelloApplication;
//import org.trainer.interval_trainer.Model.BaseItem;
//import org.trainer.interval_trainer.Model.Block;
//import org.trainer.interval_trainer.Model.Group;
//
//public class RoutinesController {
//
//    @FXML public VBox startOfList;
//    @FXML public VBox endOfList;
//    @FXML private VBox children;
//
//    @FXML private TextField name;
//    @FXML private TextField reps;
//    @FXML private Button openPopup;
//
//    @FXML private TextField routineTypeField;
//    @FXML private TextArea routineDescriptionField;
//    @FXML private TextField intervalNumberField;
//    @FXML private TextField intervalTimeField;
//
//
//
//    private final Group data = new Group();
//
//    public static PopupController popup;
//
//
////    @FXML
////    public void initialize() {
////        popup = new PopupController(this);
////        updateView();
////
////        data.getChildren().addListener(new ListChangeListener<BaseItem>() {
////            @Override
////            public void onChanged(Change<? extends BaseItem> change) {
////                updateView();
////            }
////        });
////
////
////        startOfList.setOnDragOver(new EventHandler<DragEvent>() {
////            @Override
////            public void handle(DragEvent event) {
////                if (event.getGestureSource() != startOfList && event.getDragboard().hasString()) {
////                    event.acceptTransferModes(TransferMode.MOVE);
////                }
////                event.consume();
////            }
////        });
////        startOfList.setOnDragDropped(new EventHandler<DragEvent>() {
////            @Override
////            public void handle(DragEvent event) {
////                Dragboard db = event.getDragboard();
////                boolean success = false;
////                if (db.hasString()) {
////                    System.out.println(db.getString());
////                    success = true;
////                }
////                System.out.println(event.getGestureSource());
////                BaseItem firstBlock = ((BaseController) event.getGestureSource()).getData();
////                firstBlock.getParent().getChildren().remove(firstBlock);
////
////                firstBlock.setParent(data);
////                data.getChildren().addFirst(firstBlock);
////
////                event.setDropCompleted(success);
////                event.consume();
////            }
////        });
////        endOfList.setOnDragOver(new EventHandler<DragEvent>() {
////            @Override
////            public void handle(DragEvent event) {
////                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
////                    event.acceptTransferModes(TransferMode.MOVE);
////                }
////                event.consume();
////            }
////        });
////        endOfList.setOnDragDropped(new EventHandler<DragEvent>() {
////            @Override
////            public void handle(DragEvent event) {
////                Dragboard db = event.getDragboard();
////                boolean success = false;
////                if (db.hasString()) {
////                    System.out.println(db.getString());
////                    success = true;
////                }
////                System.out.println(event.getGestureSource());
////
////                BaseItem firstBlock = ((BaseController) event.getGestureSource()).getData();
////                firstBlock.getParent().getChildren().remove(firstBlock);
////
////                firstBlock.setParent(data);
////                data.getChildren().add(firstBlock);
////
////                event.setDropCompleted(success);
////                event.consume();
////            }
////        });
////    }
//
//    public void initialize() {
//        popup = new PopupController(this);
//        updateView();
//
//        // Initialize routine with one block and one group
////        Block initialBlock = new Block();
////        Group initialGroup = new Group();
////        initialGroup.getChildren().add(initialBlock);
////        data.getChildren().add(initialGroup);
//
//        // Set up listeners for data changes
//        data.getChildren().addListener((ListChangeListener<BaseItem>) change -> updateView());
//
//        // Set up drag-and-drop functionality for reordering blocks and groups
//        setupDragAndDrop(startOfList);
//        setupDragAndDrop(endOfList);
//    }
//
//    private void setupDragAndDrop(VBox target) {
//        target.setOnDragOver(event -> {
//            if (event.getGestureSource() != target && event.getDragboard().hasString()) {
//                event.acceptTransferModes(TransferMode.MOVE);
//            }
//            event.consume();
//        });
//
//        target.setOnDragDropped(event -> {
//            Dragboard db = event.getDragboard();
//            boolean success = false;
//            if (db.hasString()) {
//                success = true;
//            }
//
//            // Handle dropping the block or group
//            BaseItem droppedItem = ((BaseController) event.getGestureSource()).getData();
//            droppedItem.getParent().getChildren().remove(droppedItem);
//            if (target == startOfList) {
//                data.getChildren().add(0, droppedItem);
//            } else {
//                data.getChildren().add(droppedItem);
//            }
//
//            event.setDropCompleted(success);
//            event.consume();
//        });
//    }
//
//
//    /**
//     * recursive function to update the view
//     */
//    private void updateView () {
//        children.getChildren().clear();
//        for (BaseItem child : data.getChildren()) {
//            if (child instanceof Block) {
//                children.getChildren().add(new BlockController((Block) child));
//
//            } else if (child instanceof Group) {
//                children.getChildren().add(new GroupController((Group) child));
//            }
//        }
//    }
//
//
//    private void showErrorAlert(String message) {
//        showAlert(Alert.AlertType.ERROR, "Error", message);
//    }
//
//    private void showAlert(Alert.AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    /**
//     * will add a new block to group variable
//     * called from add button on routine
//     */
////    public void addBlock(ActionEvent actionEvent) {
////        // Get data from input fields
////        String routineType = routineTypeField.getText();
////        String routineDescription = routineDescriptionField.getText();
////        int intervalNumber;
////        int intervalTime;
////
////        // Validate and parse interval number
////        try {
////            intervalNumber = Integer.parseInt(intervalNumberField.getText());
////            if (intervalNumber <= 0) {
////                showErrorAlert("Number of intervals must be a positive integer.");
////                return;
////            }
////        } catch (NumberFormatException e) {
////            showErrorAlert("Number of intervals must be a valid integer.");
////            return;
////        }
////
////        // Validate and parse interval time
////        try {
////            intervalTime = Integer.parseInt(intervalTimeField.getText());
////            if (intervalTime <= 0) {
////                showErrorAlert("Interval time must be a positive integer.");
////                return;
////            }
////        } catch (NumberFormatException e) {
////            showErrorAlert("Interval time must be a valid integer.");
////            return;
////        }
////
////        // Create block with routine details
////        Block newBlock = new Block();
////        newBlock.setType(routineType);
////        newBlock.setDescription(routineDescription);
////        newBlock.setIntervalNumber(intervalNumber);
////        newBlock.setIntervalTime(intervalTime);
////
////        // Calculate total time
////        int totalTime = intervalNumber * intervalTime;
////        newBlock.setTotalTime(totalTime);
////
////        // Add the block to the routine
////        newBlock.setParent(data);
////        data.getChildren().add(newBlock);
////
////        // Show success message or perform any other actions
////        showAlert(Alert.AlertType.INFORMATION, "Success", "Block added successfully.");
////
////    }
//
//    public void addBlock(ActionEvent actionEvent) {
//        Block newBlock = new Block();
//        newBlock.setParent(data);
//        data.getChildren().add(newBlock);
//    }
//
//    /**
//     * will add a new block to group variable
//     * called from add button on routine
//     *
//     */
//    public void addGroup(ActionEvent actionEvent) {
//        Group newGroup = new Group();
//        newGroup.setParent(data);
//        data.getChildren().add(newGroup);
//    }
//
//}
//
