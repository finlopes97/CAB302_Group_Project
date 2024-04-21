package org.trainer.interval_trainer.test;
import org.trainer.interval_trainer.validation.UserRegistrationValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationValidatorTest {
    @Test
    public void isValidEmail() {
        UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
        assertTrue(userRegistrationValidator.isValidEmail("user@email.com"));
    }
}
