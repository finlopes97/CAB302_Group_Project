package org.trainer.interval_trainer.validation;

public class UserRegistrationValidator {

    public boolean isValidEmail(String address) {
        // Email addresses must contain an '@' symbol
        return address != null && address.contains("@");
    }

    public boolean isvalidUsername(String username) {
        // Username must be made up of alphanumeric characters and be at least four characters long
        return username.matches("^[a-zA-Z0-9_-]{3,15}$");
    }

    public boolean isValidPassword(String password) {
        // Valid passwords must be made up of alphanumeric characters and be at least six characters long
        return password.matches("^[a-zA-Z0-9_./~!@#$%^*()&+-]{6,30}$");
    }
}
