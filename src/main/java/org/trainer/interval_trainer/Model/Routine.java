package org.trainer.interval_trainer.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;

/**
 * The {@code Routine} class represents a workout routine in the application.
 * It includes properties such as the ID, name, creator, creation timestamp, description, total time, and associated group.
 */
public class Routine {
    private IntegerProperty id = new SimpleIntegerProperty();
    /**
     * Gets the unique identifier of the routine.
     * @return the ID property
     */
    public IntegerProperty getId() { return id; }

    private StringProperty name = new SimpleStringProperty();
    /**
     * Gets the name property of the routine.
     * @return the name property
     */
    public StringProperty getName() { return name; }

    private String createdBy;
    private Timestamp createdOn;

    /**
     * Gets the timestamp when the routine was created.
     * @return the creation timestamp
     */
    public Timestamp getCreatedOn() {
        return createdOn;
    }
    /**
     * Sets the timestamp when the routine was created.
     * @param createdOn the creation timestamp to set
     */
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    private StringProperty description = new SimpleStringProperty();
    /**
     * Gets the description property of the routine.
     * @return the description property
     */
    public StringProperty getDescription() { return description; }

    private Group group;
    /**
     * Sets the group associated with this routine.
     * @param group the group to set
     */
    public void setGroup(Group group) { this.group = group; }
    /**
     * Gets the group associated with this routine.
     * @return the group
     */
    public Group getGroup() { return group; }

    private int totalTime;
    /**
     * Gets the total time of the routine in seconds.
     * @return the total time
     */
    public int getTotalTime() {
        return totalTime;
    }
    /**
     * Sets the total time of the routine in seconds.
     * @param totalTime the total time to set
     */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Constructs a new {@code Routine} with the specified properties.
     *
     * @param name the name of the routine
     * @param createdBy the username of the person who created the routine
     * @param createdOn the timestamp when the routine was created
     * @param description the description of the routine
     * @param totalTime the total time of the routine in seconds
     * @param group the group associated with the routine
     */
    public Routine(String name, String createdBy, Timestamp createdOn, String description, int totalTime, Group group) {
        this.name.set(name);
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.description.set(description);
        this.totalTime = totalTime;
        this.group = group;
    }

    /**
     * Default constructor that initializes the group to a new instance.
     */
    public Routine() {
        this.group = new Group();
    }

    /**
     * Gets the username of the person who created the routine.
     * @return the username of the creator
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the username of the person who created the routine.
     * @param createdBy the username of the creator to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}