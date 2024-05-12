package org.trainer.interval_trainer.Model;

import org.trainer.interval_trainer.Model.User;

public class Session {
    private static Session instance = null;
    private User currentUser;

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

}
