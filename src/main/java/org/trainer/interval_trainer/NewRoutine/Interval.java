package org.trainer.interval_trainer.NewRoutine;

import java.util.Timer;

public class Interval {
    public String Title;
    public String getTitle() {return Title;}
    public void setTitle(String newTitle) {this.Title = newTitle;}
    public Integer Time;
    public Integer getTime() {return Time;}
    public Interval(String Title, Integer Time) {
        this.Time = Time;
        this.Title = Title;
    }
}