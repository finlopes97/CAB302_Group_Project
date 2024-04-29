package org.trainer.interval_trainer.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DebugConnection {
    private static Connection instance = null;

    private DebugConnection() {
        String url = "jdbc:sqlite:IntervalTrainingApp.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DebugConnection();
        }
        return instance;
    }
}
