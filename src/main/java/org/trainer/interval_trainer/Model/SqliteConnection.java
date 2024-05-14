package org.trainer.interval_trainer.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    private static Connection instance = null;
    private static Session session;

    private SqliteConnection() {
        session = Session.getInstance();
        String url = session.getDB_URL();
            try {
                instance = DriverManager.getConnection(url);
            }
            catch (SQLException sqlEx) {
                System.err.println("SQLException: " + sqlEx.getMessage());
            }
    }
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}
