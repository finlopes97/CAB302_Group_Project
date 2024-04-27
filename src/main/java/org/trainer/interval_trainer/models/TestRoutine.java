package org.trainer.interval_trainer.models;

import java.sql.Timestamp;

public class TestRoutine {
    private int id;
    private String Name;
    private String CreatedBy;
    private Timestamp CreatedOn;
    private String Description;
    private int TotalTime;

    public TestRoutine(String Name, String CreatedBy, Timestamp CreatedOn, String Description, int TotalTime) {
        this.Name = Name;
        this.CreatedBy = CreatedBy;
        this.CreatedOn = CreatedOn;
        this.Description = Description;
        this.TotalTime = TotalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public Timestamp getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        CreatedOn = createdOn;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getTotalTime() {
        return TotalTime;
    }

    public void setTotalTime(int totalTime) {
        TotalTime = totalTime;
    }
}
