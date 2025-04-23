package org.example.pharmacy.services.purchaseService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MedNotFoundException extends RuntimeException{
    private MedNotFoundException(String message) {
        super(message);
    }

    public static ResponseStatusException create(long medId){
        MedNotFoundException exception = new MedNotFoundException(String.format("Med with id %s not found.", medId));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(),exception);

    }
}
