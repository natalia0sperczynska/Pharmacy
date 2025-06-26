package org.example.pharmacy.persistance.entities;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity
@Table(name = "meds", schema = "pharmacy")
public class MedEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true)
    private long id;

    @Basic
    @Column(name = "barcode", nullable = false)
    private long barcode;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "dose")
    private String dose;

    @Basic
    @Column(name = "company_name")
    private String company_name;

    @Basic
    @Column(name = "price", nullable = false)
    private Float price;

    @Basic
    @Column(name = "form")
    private String form;

    @Basic
    @Column(name = "expiry_date")
    private Date expiry_date;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "med",fetch = FetchType.LAZY)
    private List<PurchaseItemEntity> purchaseItems;

    public List<PurchaseItemEntity> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItemEntity> purchases) {
        this.purchaseItems = purchases;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
