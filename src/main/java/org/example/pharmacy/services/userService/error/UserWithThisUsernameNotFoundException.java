package org.example.pharmacy.services.userService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserWithThisUsernameNotFoundException extends RuntimeException {
    private UserWithThisUsernameNotFoundException(String message) {
        super(message);
    }
    public static ResponseStatusException create(String username){
        UserWithThisUsernameNotFoundException exception = new UserWithThisUsernameNotFoundException(String.format("User with username %s not found.", username));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(),exception);

    }
}
