package org.trainer.interval_trainer.test;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.trainer.interval_trainer.Model.Routine;

import java.sql.Timestamp;

public class ActivityTest {
    private Routine routine;
    @BeforeEach
    public void setUp() {
        Timestamp Timestamp = null;
        routine = new Routine("Routine", "User", Timestamp, "Description", 60);
    }
    @Test
    public void testGetRoutine() {

    }
}
