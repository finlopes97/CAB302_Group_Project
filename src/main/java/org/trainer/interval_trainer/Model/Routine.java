package org.trainer.interval_trainer.Model;

import java.sql.Timestamp;
import java.time.LocalTime;

public class Routine {
    private int id;
    private String Name;
    private String CreatedBy;
    private Timestamp CreatedOn;
    private String Description;
    private int TotalTime;


    public Routine(String Name, String CreatedBy, Timestamp CreatedOn, String Description, int TotalTime) {

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

    public void setFirstName(String Name) {
        this.Name = Name;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public Timestamp getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Timestamp CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    public int getTotalTime() {
        return TotalTime;
    }

    public void setTotalTime(int TotalTime) {
        this.TotalTime = TotalTime;
    }

}