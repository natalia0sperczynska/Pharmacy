package org.example.pharmacy.services.valuedObject;
//validation
public class Price {
    private final float value;

    private Price(float value) {
        this.value = value;
    }
    public float getValue() {
        return value;
    }
    public static Price create(float value){
        if(value<0){
            throw new RuntimeException("Price cannot be negative");
        }
        var roundedPrice =Math.round(value *100.0)/100.0f;
        return new Price(roundedPrice);
    }
}
