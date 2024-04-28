package org.trainer.interval_trainer.Model;

import java.util.List;

public class Group {
    private int Id;
    private int RoutineId;
    private int Intervals;
    private List<Block> Blocks;

    public Group(int Id, int RoutineId, int Intervals, List<Block> Blocks) {
        this.Id = Id;
        this.RoutineId = RoutineId;
        this.Intervals = Intervals;
        this.Blocks = Blocks;
    }

    public int getId() { return Id; }
    public int getRoutineId() { return RoutineId; }
    public int getIntervals() { return Intervals; }
    public List<Block> getBlocks() { return Blocks; }

    public void setId(int Id) { this.Id = Id; }
    public void setRoutineId(int RoutineId) { this.RoutineId = RoutineId; }
    public void setIntervals(int Intervals) { this.Intervals = Intervals; }
    public void setBlocks(List<Block> Blocks) { this.Blocks = Blocks; }
}
