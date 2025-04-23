package org.example.pharmacy.services.authService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRoleException extends RuntimeException{
    private InvalidRoleException(String message){
        super(message);
    }
    public static ResponseStatusException create(String role){
        InvalidRoleException exception = new InvalidRoleException(String.format("Invalid role %s, available : ROLE_ADMIN, ROLE_USER", role));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getLocalizedMessage(),exception);

    }
}
