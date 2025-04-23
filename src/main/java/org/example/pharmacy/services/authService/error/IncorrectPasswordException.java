package org.example.pharmacy.services.authService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectPasswordException extends RuntimeException{
    private IncorrectPasswordException(String message){
        super(message);
    }
    public static ResponseStatusException create(){
        IncorrectPasswordException exception = new IncorrectPasswordException("Incorrect password");
        return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getLocalizedMessage(),exception);
    }

}
