package org.trainer.interval_trainer.Model;

/**
 * The {@code BaseItem} class represents an abstract base class for items in the application.
 * It includes common properties and methods for items, such as an identifier and a reference to a parent group.
 */
public abstract class BaseItem {
    protected int Id;

    protected Group parent;

    /**
     * Gets the parent group of this item.
     * @return the parent group
     */
    public Group getParent() { return parent; }
    /**
     * Sets the parent group of this item.
     * @param parent the parent group to set
     */
    public void setParent(Group parent) { this.parent = parent; }

    /**
     * Gets the unique identifier of this item.
     * @return the unique identifier
     */
    public int getId() { return Id; }

    /**
     * Sets the unique identifier of this item.
     * @param id the unique identifier to set
     */
    public void setId(int id) { Id = id; }
}
