package org.example.pharmacy.services.purchaseService.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuantityException extends RuntimeException {
    private QuantityException(String message) {
        super(message);
    }

    public static ResponseStatusException create(String medName, int availableQuantity) {
        QuantityException exception = new QuantityException(String.format("Not enough quantity %s for the %s",availableQuantity,medName));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getLocalizedMessage(),exception);
    }
}


