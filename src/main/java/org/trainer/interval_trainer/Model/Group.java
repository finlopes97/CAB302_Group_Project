package org.trainer.interval_trainer.Model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class Group extends BaseItem {
    private int RoutineId;


    public int getRoutineId() { return RoutineId; }
    private final IntegerProperty reps = new SimpleIntegerProperty();
    public Property<Integer> getRepsObject() {
        return reps.asObject();
    }
    public IntegerProperty getReps() {
        return reps;
    }

    private final StringProperty name = new SimpleStringProperty();
    public StringProperty getName() {
        return name;
    }

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
