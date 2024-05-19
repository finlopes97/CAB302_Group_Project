package org.trainer.interval_trainer.Model;

/**
 * The {@code Session} class represents a singleton session for the application.
 * It holds the current user and the database URL, ensuring that only one instance of the session exists.
 */
public class Session {
    private static Session instance = null;
    private User currentUser;
    private String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";

    /**
     * Private constructor to prevent instantiation.
     */
    private Session() {
    }

    /**
     * Returns the single instance of the session.
     * If the instance does not exist, it is created.
     * @return the single instance of the session
     */
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    /**
     * Gets the current user of the session.
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    /**
     * Sets the current user of the session.
     * @param currentUser the user to set as the current user
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Gets the database URL.
     * @return the database URL
     */
    public String getDB_URL() {
        return DB_URL;
    }
    /**
     * Sets the database URL.
     * @param DB_URL the database URL to set
     */
    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }
}
