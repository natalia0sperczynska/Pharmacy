package org.example.pharmacy.controllers.DTO.purchaseDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PurchaseItemDTO {
    @NotNull
    private Long medId;

    @NotNull
    @Min(1)
    private Integer quantity;


    public PurchaseItemDTO(Long medId, Integer quantity) {
        this.medId = medId;
        this.quantity = quantity;
    }

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
