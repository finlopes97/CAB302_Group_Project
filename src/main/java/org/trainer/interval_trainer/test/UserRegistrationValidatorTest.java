package org.trainer.interval_trainer.test;
import org.trainer.interval_trainer.validation.UserRegistrationValidator;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserRegistrationValidatorTest {
    @Test
    public void isValidEmail() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertTrue(userRegistrationValidator.isValidEmail("user@email.com"));
    }

    @Test
    public void isValidUsername() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertTrue(userRegistrationValidator.isValidUsername("username123"));
    }

    @Test
    public void isInvalidUsername() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertFalse(userRegistrationValidator.isValidUsername("<!special!>"));
    }

    @Test
    public void isUsernameTooLong() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertFalse(userRegistrationValidator.isValidUsername("hack the planet!!!"));
    }

    @Test
    public void isUsernameTooShort() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertFalse(userRegistrationValidator.isValidUsername("UwU"));
    }

    @Test
    public void isValidPassword() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertTrue(userRegistrationValidator.isValidPassword("password123"));
    }

    @Test
    public void isPasswordAbleToUseSpecialCharacters() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertTrue(userRegistrationValidator.isValidPassword("password!@#"));
    }

    @Test
    public void isInvalidPassword() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertFalse(userRegistrationValidator.isValidPassword("no spaces in password"));
    }

    @Test
    public void isPasswordTooShort() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertFalse(userRegistrationValidator.isValidPassword("guest"));
    }

    @Test
    public void isPasswordTooLong() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertFalse(userRegistrationValidator.isValidPassword("residentevildeadaimdoesntgetenoughcreditforbeingafunlightgungameonthesonyplaystation2"));
    }
}
