package org.example.pharmacy.controllers.DTO.medDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class AddMedDTO {

    @NotNull(message = "Price required")
    private long barcode;

    @NotNull(message = "Name of the med required")
    private String name;
    private String dose;

    @NotNull(message = "Company name required")
    private String company_name;
    @Min(value = 0, message = "Price cannot be negative")
    private Float price;
    private String form;
    @NotNull
    private Date expiry_date;
    @Min(value = 0, message = "Price cannot be negative")
    private int quantity;

    public AddMedDTO() {
    }

    public AddMedDTO(long barcode, String name, String dose, String company_name, Float price, String form, Date expiry_date, int quantity) {
        this.barcode = barcode;
        this.name = name;
        this.dose = dose;
        this.company_name = company_name;
        this.price = price;
        this.form = form;
        this.expiry_date = expiry_date;
        this.quantity = quantity;
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
