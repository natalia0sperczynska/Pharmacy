package org.example.pharmacy.services.medsService.error;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MedNotFound {
    public static ResponseStatusException create(long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("med with this id not found", id));
    }
}
