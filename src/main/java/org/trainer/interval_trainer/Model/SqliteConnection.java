package org.trainer.interval_trainer.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.trainer.interval_trainer.Model.Session;

public class SqliteConnection {
    private static Connection instance = null;
    private static Session session;

    private SqliteConnection() {
        session = Session.getInstance();
        String url = session.getDbUrl;
            try {
                instance = DriverManager.getConnection(url);
            }
            catch (SQLException sqlEx) {
                System.err.println(sqlEx);
            }
    }
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}
