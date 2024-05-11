package org.trainer.interval_trainer.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Block extends BaseItem {
    private int RoutineId;
    public int getRoutineId() { return RoutineId; }
    private int GroupId;
    public int getGroupId() { return RoutineId; }


    private final StringProperty name = new SimpleStringProperty();
    public StringProperty getName() {
        return name;
    }
    private final IntegerProperty timeInSeconds  = new SimpleIntegerProperty();
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


    public void setType(String routineType) {
    }

    public void setDescription(String routineDescription) {
    }

    public void setIntervalNumber(int intervalNumber) {
    }

    public void setIntervalTime(int intervalTime) {
    }

    public void setTotalTime(int totalTime) {
    }
}