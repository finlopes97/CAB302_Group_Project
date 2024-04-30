package org.trainer.interval_trainer.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class Group extends BaseItem {
    private int RoutineId;


    public int getRoutineId() { return RoutineId; }
    private final IntegerProperty reps = new SimpleIntegerProperty();
    public IntegerProperty getReps() {
        return reps;
    }

    private final ObservableStringValue name = new SimpleStringProperty();

    private final ObservableList<BaseItem> children =  observableArrayList();
    public ObservableList<BaseItem> getChildren() {
        return children;
    }



    public Group(int Id, int RoutineId, int Intervals, List<BaseItem> children) {
        setId(Id);
    }

    public Group() {

    }


}
