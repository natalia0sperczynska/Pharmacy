package org.example.pharmacy.controllers.DTO.purchaseDTO;

import jakarta.validation.constraints.NotNull;
import org.example.pharmacy.types.PaymentMethods;

import java.sql.Date;
public class AddPurchaseDTO {
    @NotNull
    private Date purchaseDate;
    @NotNull
    private PaymentMethods paymentMethod;
    @NotNull
    private Long userId;
    @NotNull
    private Long medId;

    public AddPurchaseDTO(Date purchaseDate, PaymentMethods paymentMethod, Long userId, Long medId) {
        this.purchaseDate = purchaseDate;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
        this.medId = medId;
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

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }
}
