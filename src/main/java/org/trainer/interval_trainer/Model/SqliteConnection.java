package org.trainer.interval_trainer.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code SqliteConnection} class provides a singleton connection to the SQLite database.
 * It ensures that only one instance of the database connection exists throughout the application.
 */
public class SqliteConnection {
    private static Connection instance = null;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the database connection using the URL from the session.
     */
    private SqliteConnection() {
        Session session = Session.getInstance();
        String url = session.getDB_URL();
            try {
                instance = DriverManager.getConnection(url);
            }
            catch (SQLException sqlEx) {
                System.err.println("SQLException: " + sqlEx.getMessage());
            }
    }

    /**
     * Returns the single instance of the database connection.
     * If the instance does not exist, it is created.
     * @return the single instance of the database connection
     */
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}
