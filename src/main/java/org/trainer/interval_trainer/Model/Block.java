package org.trainer.interval_trainer.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Block extends BaseItem implements Externalizable {
    private int RoutineId;
    public int getRoutineId() { return RoutineId; }
    private int GroupId;
    public int getGroupId() { return RoutineId; }


    private final StringProperty name = new SimpleStringProperty("");
    public StringProperty getName() {
        return name;
    }
    private final IntegerProperty timeInSeconds  = new SimpleIntegerProperty(0);
    public IntegerProperty getTimeinSeconds() {
        return timeInSeconds;
    }


    public Block(int Id, int RoutineId, int GroupId, String Type, int TimeInSeconds) {
        setId(Id);
        this.RoutineId = RoutineId;
        this.GroupId = GroupId;
        this.timeInSeconds.set(TimeInSeconds);
    }

    public Block() {

    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name.get());
        out.writeInt(timeInSeconds.get());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name.set(in.readUTF());
        timeInSeconds.set(in.readInt());
    }
}