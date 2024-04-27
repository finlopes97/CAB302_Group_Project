package org.trainer.interval_trainer.models;

import java.sql.Timestamp;

public class SharedRoutine extends TestRoutine {

    private Timestamp SharedOn;
    private boolean UseAlias;
    private String UserAlias;

    public SharedRoutine(String Name, String CreatedBy, Timestamp CreatedOn, String Description, int TotalTime, Timestamp SharedOn, boolean UseAlias, String UserAlias) {
        super(Name, CreatedBy, CreatedOn, Description, TotalTime);
        this.SharedOn = SharedOn;
        this.UseAlias = UseAlias;
        this.UserAlias = UserAlias;
    }

    public Timestamp getSharedOn() {
        return SharedOn;
    }

    public void setSharedOn(Timestamp SharedOn) {
        this.SharedOn = SharedOn;
    }

    public boolean isUseAlias() {
        return UseAlias;
    }

    public void setUseAlias(boolean UseAlias) {
        this.UseAlias = UseAlias;
    }

    public String getUserAlias() {
        return UserAlias;
    }

    public void setUserAlias(String UserAlias) {
        this.UserAlias = UserAlias;
    }
}
