package org.trainer.interval_trainer.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The {@code Block} class represents a block within a routine.
 * It includes properties such as the routine ID, group ID, name, and time in seconds.
 * This class is serializable and implements the {@code Externalizable} interface for custom serialization.
 */
public class Block extends BaseItem implements Externalizable {
    private int RoutineId;
    /**
     * Gets the ID of the routine this block belongs to.
     * @return the routine ID
     */
    public int getRoutineId() { return RoutineId; }

    private int GroupId;
    /**
     * Gets the ID of the group this block belongs to.
     * @return the group ID
     */
    public int getGroupId() { return RoutineId; }


    private final StringProperty name = new SimpleStringProperty("");
    /**
     * Gets the name property of the block.
     * @return the name property
     */
    public StringProperty getName() {
        return name;
    }

    private final IntegerProperty timeInSeconds  = new SimpleIntegerProperty(0);
    /**
     * Gets the time in seconds property of the block.
     * @return the time in seconds property
     */
    public IntegerProperty getTimeInSeconds() {
        return timeInSeconds;
    }

    /**
     * Constructs a new {@code Block} with the specified properties.
     *
     * @param Id the unique identifier for the block
     * @param RoutineId the ID of the routine this block belongs to
     * @param GroupId the ID of the group this block belongs to
     * @param Type the type of the block (currently not used)
     * @param TimeInSeconds the duration of the block in seconds
     */
    public Block(int Id, int RoutineId, int GroupId, String Type, int TimeInSeconds) {
        setId(Id);
        this.RoutineId = RoutineId;
        this.GroupId = GroupId;
        this.timeInSeconds.set(TimeInSeconds);
    }

    /**
     * Default constructor required for the {@code Externalizable} interface.
     */
    public Block() {
    }

    /**
     * Writes the object's state to the specified {@code ObjectOutput} stream.
     * @param out the {@code ObjectOutput} stream to write to
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name.get());
        out.writeInt(timeInSeconds.get());
    }

    /**
     * Reads the object's state from the specified {@code ObjectInput} stream.
     * @param in the {@code ObjectInput} stream to read from
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name.set(in.readUTF());
        timeInSeconds.set(in.readInt());
    }
}