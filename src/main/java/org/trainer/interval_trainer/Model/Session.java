package org.trainer.interval_trainer.Model;

public class Session {
    private static Session instance = null;
    private User currentUser;
    private String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";

    private Session() {
        // Private constructor to prevent instantiation
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getDB_URL() {
        return DB_URL;
    }
    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }
}
