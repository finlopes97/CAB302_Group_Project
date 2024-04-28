package org.trainer.interval_trainer.Model;

import java.sql.Timestamp;
import java.util.List;

public class TestRoutine extends BaseItem {
    private String Name;
    private String CreatedBy;
    private Timestamp CreatedOn;
    private String Description;
    private int TotalTime;
    private List<Group> Groups;
    private List<Block> Blocks;

    public TestRoutine(
            int Id,
            String Name,
            String CreatedBy,
            Timestamp CreatedOn,
            String Description,
            int TotalTime,
            List<Group> Groups,
            List<Block> Blocks) {
        setId(Id);
        this.Name = Name;
        this.CreatedBy = CreatedBy;
        this.CreatedOn = CreatedOn;
        this.Description = Description;
        this.TotalTime = TotalTime;
        this.Groups = Groups;
        this.Blocks = Blocks;
    }

    public int getId() { return Id; }
    public String getName() { return Name; }
    public String getCreatedBy() { return CreatedBy; }
    public Timestamp getCreatedOn() { return CreatedOn; }
    public String getDescription() { return Description; }
    public int getTotalTime() { return TotalTime; }
    public List<Group> getGroups() { return Groups; }
    public List<Block> getBlocks() { return Blocks; }

    public void setId(int Id) { this.Id = Id; }
    public void setName(String Name) { this.Name = Name; }
    public void setCreatedBy(String CreatedBy) { this.CreatedBy = CreatedBy; }
    public void setCreatedOn(Timestamp CreatedOn) { this.CreatedOn = CreatedOn; }
    public void setDescription(String Description) { this.Description = Description; }
    public void setTotalTime(int TotalTime) { this.TotalTime = TotalTime; }
    public void setGroups(List<Group> Groups) { this.Groups = Groups; }
    public void setBlocks(List<Block> Blocks) { this.Blocks = Blocks; }
}
