package org.example.pharmacy.controllers.DTO.purchaseDTO;

public class PurchaseItemResponseDTO {
    private Long medId;
    private String medName;
    private int quantity;
    private Float unitPrice;
    private Float totalPrice; // quantity * unitPrice

    public PurchaseItemResponseDTO(Long medId, String medName, int quantity, Float unitPrice, Float totalPrice) {
        this.medId = medId;
        this.medName = medName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
