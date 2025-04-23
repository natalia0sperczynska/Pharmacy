package org.example.pharmacy.services.authService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends RuntimeException{
    private UserNotFoundException(String message){
        super(message);
    }
    public static ResponseStatusException create(String username){
        UserNotFoundException userNotFoundException = new UserNotFoundException(String.format("User with %s username not found",username));
        return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, userNotFoundException.getLocalizedMessage(),userNotFoundException);
    }
}
