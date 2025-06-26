package org.example.pharmacy.persistance.entities;
import jakarta.persistence.*;
import org.example.pharmacy.types.PaymentMethods;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases", schema = "pharmacy")
public class PurchaseEntity {
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy =GenerationType.IDENTITY)
   private long id;

   @Column(name = "purchase_date", nullable = false)
   @Basic
   private Date purchaseDate;

    @Column(name = "payment_method",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItemEntity> items = new ArrayList<>();

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PurchaseItemEntity> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemEntity> items) {
        this.items = items;
    }
}
