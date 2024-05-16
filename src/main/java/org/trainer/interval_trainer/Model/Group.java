package org.trainer.interval_trainer.Model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import java.io.Externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class Group extends BaseItem implements Externalizable {
    private int RoutineId;


    public int getRoutineId() { return RoutineId; }
    private final IntegerProperty reps = new SimpleIntegerProperty(1);
    public Property<Integer> getRepsObject() {
        return reps.asObject();
    }
    public IntegerProperty getReps() {
        return reps;
    }

    private final StringProperty name = new SimpleStringProperty("");
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


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name.get());
        out.writeInt(reps.get());
        out.writeObject(children.stream().toList());

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name.set(in.readUTF());
        this.reps.set(in.readInt());
        this.children.setAll((List<BaseItem>) in.readObject());

        for (BaseItem child : children) {
            child.setParent(this);
        }
    }
}
