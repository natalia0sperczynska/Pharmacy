package org.example.pharmacy.persistance.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users", schema = "pharmacy")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Basic
    private String name;
    @Column(name = "last_name")
    @Basic
    private String lastName;

    @Column(name = "email",nullable = false)
    @Basic
    private String email;

    @Column(name = "phone_number")
    @Basic
    private long phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PurchaseEntity> purchases;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<PurchaseEntity> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseEntity> purchases) {
        this.purchases = purchases;
    }
}
