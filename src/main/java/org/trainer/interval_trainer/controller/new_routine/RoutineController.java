package org.trainer.interval_trainer.controller.new_routine;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.*;
import org.trainer.interval_trainer.controller.MainController;

import java.io.*;
import java.sql.Timestamp;

public class RoutineController {

    @FXML public VBox startOfList;
    @FXML public VBox endOfList;
    @FXML private VBox children;

    @FXML private TextField name;
    @FXML private TextArea description;

    private Routine routine = new Routine();
    private Group data = routine.getGroup();

    public static PopupController popup;


    public void setRoutine(Routine routine) {
        this.routine = routine;
        this.data = this.routine.getGroup();
        updateView();
        name.textProperty().bindBidirectional(routine.getName());
        description.textProperty().bindBidirectional(routine.getDescription());
    }

    @FXML
    public void initialize() {
        HelloApplication.getPrimaryStage().getScene().getStylesheets().add("/stylesheet.css");

        name.textProperty().bindBidirectional(routine.getName());
        description.textProperty().bindBidirectional(routine.getDescription());


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

    public void save(ActionEvent event) throws IOException {
        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();

        routine.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        routine.setCreatedBy(Session.getInstance().getCurrentUser().getName());
        routine.setTotalTime(0);
        if (routine.getId().get() != 0) {
            System.out.println("updated routine");
            dao.updateRoutine(routine);
        } else {
            System.out.println("added new routine");
            dao.addRoutine(routine);
        }
        exit();
    }

    public void exit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/trainer/interval_trainer/main-view.fxml"));
        HelloApplication.getPrimaryStage().getScene().setRoot(loader.load());
        ((MainController) loader.getController()).switchContent("/org/trainer/interval_trainer/manage_routines/my-routine-view.fxml");
    }

}

