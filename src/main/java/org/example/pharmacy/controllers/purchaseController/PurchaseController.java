package org.example.pharmacy.controllers.purchaseController;
import org.example.pharmacy.controllers.DTO.purchaseDTO.AddPurchaseDTO;
import org.example.pharmacy.controllers.DTO.purchaseDTO.AddPurchaseResponseDTO;
import org.example.pharmacy.controllers.DTO.purchaseDTO.GetPurchaseResponseDTO;
import org.example.pharmacy.persistance.entities.PurchaseEntity;
import org.example.pharmacy.services.purchaseService.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<GetPurchaseResponseDTO> getOneById(@PathVariable @Validated long id) {
        var purchase =  purchaseService.getPurchaseById(id);
        return new ResponseEntity<GetPurchaseResponseDTO>(purchase, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<GetPurchaseResponseDTO>> getAll(@RequestBody @Validated AddPurchaseDTO purchase) {
        //all for specific, user all all for admin
        List<GetPurchaseResponseDTO> purchases = purchaseService.getAllPurchases();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

}
