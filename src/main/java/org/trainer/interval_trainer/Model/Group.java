package org.trainer.interval_trainer.Model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import java.io.Externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * The {@code Group} class represents a group of blocks or subgroups.
 * It includes properties such as the routine ID, repetitions, name, and a list of child items.
 * This class is serializable and implements the {@code Externalizable} interface for custom serialization.
 */
public class Group extends BaseItem implements Externalizable {
    private int RoutineId;
    /**
     * Gets the ID of the routine this group belongs to.
     * @return the routine ID
     */
    public int getRoutineId() { return RoutineId; }

    private final IntegerProperty reps = new SimpleIntegerProperty(1);
    /**
     * Gets the repetitions property of the group.
     * @return the repetitions property
     */
    public Property<Integer> getRepsObject() {
        return reps.asObject();
    }
    /**
     * Gets the repetitions property of the group.
     * @return the repetitions property
     */
    public IntegerProperty getReps() {
        return reps;
    }

    private final StringProperty name = new SimpleStringProperty("");
    /**
     * Gets the name property of the group.
     * @return the name property
     */
    public StringProperty getName() {
        return name;
    }

    private final ObservableList<BaseItem> children =  observableArrayList();
    /**
     * Gets the list of child items within this group.
     * @return the list of child items
     */
    public ObservableList<BaseItem> getChildren() {
        return children;
    }

    /**
     * Constructs a new {@code Group} with the specified properties.
     * @param Id the unique identifier for the group
     * @param RoutineId the ID of the routine this group belongs to
     * @param Intervals the number of intervals (currently not used)
     * @param children the list of child items within this group
     */
    public Group(int Id, int RoutineId, int Intervals, List<BaseItem> children) {
        setId(Id);
    }

    /**
     * Default constructor required for the {@code Externalizable} interface.
     */
    public Group() {
    }

    /**
     * Writes the object's state to the specified {@code ObjectOutput} stream.
     * @param out the {@code ObjectOutput} stream to write to
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name.get());
        out.writeInt(reps.get());
        out.writeObject(children.stream().toList());

    }

    /**
     * Reads the object's state from the specified {@code ObjectInput} stream.
     * @param in the {@code ObjectInput} stream to read from
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
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
