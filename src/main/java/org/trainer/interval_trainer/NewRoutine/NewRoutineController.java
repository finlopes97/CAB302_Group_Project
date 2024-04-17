package org.trainer.interval_trainer.NewRoutine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

public class NewRoutineController {

    public class IntervalCellFactory implements Callback<ListView<Interval>, ListCell<Interval>> {

        @Override
        public ListCell<Interval> call(ListView<Interval> param) {
            return new IntervalCell();
        }
    }


    public class IntervalCell extends ListCell<Interval> {

        @FXML private Button remove;
        @FXML private Button addbelow;
        @FXML private Button addabove;
        @FXML private TextField text;

        public IntervalCell() {
            loadFXML();

            text.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) {
                    listView.getItems().get(getIndex()).setTitle(text.getText());
                }
            });
            text.setOnAction(event -> {
                listView.getItems().get(getIndex()).setTitle(text.getText());
            });

            remove.setOnAction(event -> {
                listView.getItems().remove(getIndex());
                System.out.println(event);
            });
            addbelow.setOnAction(event -> {
                listView.getItems().add(getIndex()+1, new Interval("", 0));
            });
            addabove.setOnAction(event -> {
                listView.getItems().add(getIndex(), new Interval("", 0));
            });



        }

        private void loadFXML() {
            try {
                URL fxmlLocation = getClass().getResource("/interval-cell.fxml");
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                loader.setController(this);
                loader.setRoot(this);
                loader.load();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void updateItem(Interval item, boolean empty) {
            super.updateItem(item, empty);

            if(empty || item == null) {
                setText(null);
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
            else {
                text.setText(item.getTitle());

                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }




    @FXML
    private ListView<Interval> listView = new ListView<>();
    @FXML
    private void initialize() {

        listView.setCellFactory(new IntervalCellFactory());
    }


    public void debug(String id){
        System.out.println(id);

    }

    public void OnNewIntervalButton(ActionEvent actionEvent) {
        listView.getItems().add(new Interval("", 0));
    }
}
