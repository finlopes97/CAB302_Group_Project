package org.trainer.interval_trainer.Model;

public abstract class BaseItem {
    protected int Id;

    protected Group parent;

    public Group getParent() { return parent; }
    public void setParent(Group parent) { this.parent = parent; }

    public int getId() { return Id; }
    public void setId(int id) { Id = id; }

}
