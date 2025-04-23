package org.example.pharmacy.services.purchaseService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends RuntimeException {
    private UserNotFoundException(String message) {
        super(message);
    }
    public static ResponseStatusException create(long userId){
        UserNotFoundException exception = new UserNotFoundException(String.format("User with id %s not found.", userId));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(),exception);

    }
}
