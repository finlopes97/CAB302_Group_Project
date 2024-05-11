package org.trainer.interval_trainer.validation;

import java.util.regex.Pattern;

public class UserRegistrationValidator {
    Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.[A-Za-z]+$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]{4,30}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9_./~!@#$%^*()&+-]{6,30}$");

    /**
     * This method checks to make sure an email address is valid
     * @param address string
     * @return bool
     */
    public boolean isValidEmail(String address) {
        return address != null && EMAIL_PATTERN.matcher(address).matches();
    }

    /**
     * This method checks to make sure a username is at least four characters long and is made up of
     * alphanumeric characters with no spaces or special characters
     * @param username string
     * @return bool
     */
    public boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    /**
     * This method checks that passwords are made up of alphanumeric characters and be at least six characters long
     * @param password string
     * @return bool
     */
    public boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }
}
