package org.trainer.interval_trainer.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;

public class Routine {
    private IntegerProperty id = new SimpleIntegerProperty();

    public IntegerProperty getId() { return id; }

    private StringProperty name = new SimpleStringProperty();
    public StringProperty getName() { return name; }
    private String createdBy;
    private Timestamp createdOn;

    public Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Timestamp CreatedOn) {
        this.createdOn = CreatedOn;
    }

    private StringProperty description = new SimpleStringProperty();
    public StringProperty getDescription() { return description; }

    private Group group;
    public void setGroup(Group group) { this.group = group; }
    public Group getGroup() { return group; }

    private int totalTime;
    public int getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(int TotalTime) {
        this.totalTime = TotalTime;
    }

    public Routine(String name, String createdBy, Timestamp createdOn, String description, int totalTime, Group group) {
        this.name.set(name);
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.description.set(description);
        this.totalTime = totalTime;
        this.group = group;
    }

    public Routine() {
        this.group = new Group();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.createdBy = CreatedBy;
    }
}