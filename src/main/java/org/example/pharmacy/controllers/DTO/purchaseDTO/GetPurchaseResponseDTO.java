package org.example.pharmacy.controllers.DTO.purchaseDTO;

import org.example.pharmacy.controllers.DTO.medDTO.GetMedDTO;
import org.example.pharmacy.controllers.DTO.userDTO.GetUserDTO;
import org.example.pharmacy.types.PaymentMethods;

import java.sql.Date;

public class GetPurchaseResponseDTO {
    private long id;
    private Date purchaseDate;
    private PaymentMethods paymentMethod;
    private GetUserDTO user;
    private GetMedDTO med;

    public GetPurchaseResponseDTO(long id, Date purchaseDate, PaymentMethods paymentMethod, GetUserDTO user, GetMedDTO med) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.paymentMethod = paymentMethod;
        this.user = user;
        this.med = med;
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

    public GetUserDTO getUser() {
        return user;
    }

    public void setUser(GetUserDTO user) {
        this.user = user;
    }

    public GetMedDTO getMed() {
        return med;
    }

    public void setMed(GetMedDTO med) {
        this.med = med;
    }
}
