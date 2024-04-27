package org.trainer.interval_trainer.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSqliteConnection {
    private static Connection instance = null;

    private TestSqliteConnection() {
        String url = "jdbc:sqlite:IntervalTrainingApp.db";
        try {
            instance = DriverManager.getConnection(url);
        }
        catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }
    public static Connection getInstance() {
        if (instance == null) {
            new TestSqliteConnection();
        }
        return instance;
    }
}