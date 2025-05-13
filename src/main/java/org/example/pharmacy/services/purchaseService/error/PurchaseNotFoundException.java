package org.example.pharmacy.services.purchaseService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PurchaseNotFoundException extends RuntimeException{
    private PurchaseNotFoundException(String message) {
        super(message);
    }

    public static ResponseStatusException create(long medId){
        PurchaseNotFoundException exception = new PurchaseNotFoundException(String.format("Purchase with id %s not found.", medId));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(),exception);

    }
}
