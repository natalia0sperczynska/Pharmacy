package org.example.pharmacy.controllers.DTO.medDTO;

import java.sql.Date;

public class
GetMedDTO {
    private long id;
    private long barcode;
    private String name;
    private String dose;
    private String company_name;
    private Float price;
    private String form;
    private Date expiry_date;
    private int quantity;

    public GetMedDTO() {
    }

    public GetMedDTO(long id, long barcode, String name, String dose, String company_name, Float price, String form, Date expiry_date, int quantity) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.dose = dose;
        this.company_name = company_name;
        this.price = price;
        this.form = form;
        this.expiry_date = expiry_date;
        this.quantity = quantity;
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


