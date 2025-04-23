package org.example.pharmacy.services.authService.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidEmailException extends RuntimeException{
    private InvalidEmailException(String message){
        super(message);
    }
    public static ResponseStatusException create(String email){
        InvalidEmailException invalidEmailException = new InvalidEmailException(String.format("Email %s is not a valid email",email));
        return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, invalidEmailException.getLocalizedMessage(),invalidEmailException);
    }
}
