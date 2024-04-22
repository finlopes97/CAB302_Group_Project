package org.trainer.interval_trainer.validation;

/**
 * Contains methods for validating user input on account creations
 */
public class UserRegistrationValidator {
    /**
     * This method checks to make sure an email address contains an '@' symbol
     * @param address string
     * @return bool
     */
    public boolean isValidEmail(String address) {
        return address != null && address.contains("@");
    }
    /**
     * This method checks to make sure a username is at least four characters long and is made up of
     * alphanumeric characters with no spaces or special characters
     * @param username string
     * @return bool
     */
    public boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_-]{4,15}$");
    }
    /**
     * This method checks that passwords are made up of alphanumeric characters and be at least six characters long
     * @param password string
     * @return bool
     */
    public boolean isValidPassword(String password) {
        return password.matches("^[a-zA-Z0-9_./~!@#$%^*()&+-]{6,30}$");
    }
}
