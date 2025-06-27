package org.example.pharmacy.persistance.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase_item", schema = "pharmacy")
public class PurchaseItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private PurchaseEntity purchase;

    @ManyToOne
    @JoinColumn(name = "med_id", nullable = false)
    private MedEntity med;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }

    public MedEntity getMed() {
        return med;
    }

    public void setMed(MedEntity med) {
        this.med = med;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }
}