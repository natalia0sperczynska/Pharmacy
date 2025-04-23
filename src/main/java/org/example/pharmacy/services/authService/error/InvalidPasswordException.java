package org.example.pharmacy.services.authService.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException extends RuntimeException{
    private InvalidPasswordException(String message) {
        super(message);
    }

    public static ResponseStatusException create(String password){
        InvalidPasswordException exception = new InvalidPasswordException(String.format("Your %s password is too weak. Password needs to have: min 8 characters, a lowercase letter, uppercase letter, a number and a special digit", password));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getLocalizedMessage(),exception);

    }
}
