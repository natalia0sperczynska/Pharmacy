package org.example.pharmacy.services.valuedObject;

import org.example.pharmacy.services.authService.error.InvalidEmailException;

public class Email {
    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public static Email create(String email) {
        if (email == null || email.isBlank() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw InvalidEmailException.create(email);
        }
        return new Email(email);
    }
    //regex ^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$
}
