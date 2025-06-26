package org.example.pharmacy.controllers.DTO.purchaseDTO;

import org.example.pharmacy.types.PaymentMethods;

import java.sql.Date;
import java.util.List;

public class AddPurchaseResponseDTO {
    private long id;
    private Date purchaseDate;
    private PaymentMethods paymentMethod;
    private Long userId;
    private List<PurchaseItemResponseDTO> items;

    public AddPurchaseResponseDTO(long id, Date purchaseDate, PaymentMethods paymentMethod, Long userId, List<PurchaseItemResponseDTO> items) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<PurchaseItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemResponseDTO> items) {
        this.items = items;
    }
}