package org.trainer.interval_trainer.validation;

public class UserRegistrationValidator {

    public boolean isValidEmail(String address) {
        return address != null && address.contains("@");
    }
}
