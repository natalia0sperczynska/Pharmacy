package org.example.pharmacy.controllers.DTO.purchaseDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.pharmacy.types.PaymentMethods;

import java.sql.Date;
import java.util.List;

public class AddPurchaseDTO {
    @NotNull
    private Date purchaseDate;
    @NotNull
    private PaymentMethods paymentMethod;
    @NotNull
    private Long userId;
    @NotNull
    @Size(min = 1, message = "At least one medicine is required")
    private List<PurchaseItemDTO> items;

    public AddPurchaseDTO(Date purchaseDate, PaymentMethods paymentMethod, Long userId, List<PurchaseItemDTO> items) {
        this.purchaseDate = purchaseDate;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
        this.items = items;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public PaymentMethods getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PurchaseItemDTO> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemDTO> items) {
        this.items = items;
    }
}
