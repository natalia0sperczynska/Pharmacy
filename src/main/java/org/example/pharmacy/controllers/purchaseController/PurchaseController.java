package org.example.pharmacy.controllers.purchaseController;
import org.example.pharmacy.controllers.DTO.purchaseDTO.AddPurchaseDTO;
import org.example.pharmacy.controllers.DTO.purchaseDTO.AddPurchaseResponseDTO;
import org.example.pharmacy.persistance.entities.PurchaseEntity;
import org.example.pharmacy.services.purchaseService.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchases")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping()
    public ResponseEntity<AddPurchaseResponseDTO> create(@RequestBody @Validated AddPurchaseDTO purchase) {
        var newPurchase = purchaseService.create(purchase);
        return new ResponseEntity<AddPurchaseResponseDTO>(newPurchase, HttpStatus.CREATED);
    }

}
