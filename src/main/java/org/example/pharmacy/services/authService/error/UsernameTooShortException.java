package org.example.pharmacy.services.authService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameTooShortException extends RuntimeException{
    private UsernameTooShortException (String message){
        super(message);
    }

    public static ResponseStatusException create(String username){
        UsernameTooShortException exception = new UsernameTooShortException(String.format("%s username is too short. Minimum 5 characters",username));
        return new ResponseStatusException(HttpStatus.LENGTH_REQUIRED,exception.getMessage(),exception);
    }
}
