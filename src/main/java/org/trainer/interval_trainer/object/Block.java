package org.trainer.interval_trainer.object;

public class Block {
    private int Id;
    private int RoutineId;
    private int GroupId;
    private String Type;
    private int TimeInSeconds;

    public Block(int Id, int RoutineId, int GroupId, String Type, int TimeInSeconds) {
        this.Id = Id;
        this.RoutineId = RoutineId;
        this.GroupId = GroupId;
        this.Type = Type;
        this.TimeInSeconds = TimeInSeconds;
    }

    public int getId() { return Id; }
    public int getRoutineId() { return RoutineId; }
    public int getGroupId() { return GroupId; }
    public String getType() { return Type; }
    public int getTimeInSeconds() { return TimeInSeconds; }

    public void setId(int Id) { this.Id = Id; }
    public void setRoutineId(int RoutineId) { this.RoutineId = RoutineId; }
    public void setGroupId(int GroupId) { this.GroupId = GroupId; }
    public void setType(String Type) { this.Type = Type; }
    public void setTimeInSeconds(int TimeInSeconds) { this.TimeInSeconds = TimeInSeconds; }
}