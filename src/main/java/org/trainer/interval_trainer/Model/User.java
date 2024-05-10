package org.trainer.interval_trainer.Model;

public class User {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    // Override toString() to return the email address
    @Override
    public String toString() {
        return email;
    }
}
