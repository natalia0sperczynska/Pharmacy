package org.example.pharmacy.controllers.DTO.userDTO;

import org.openapitools.jackson.nullable.JsonNullable;

public class PatchUserDTO {
    private JsonNullable<String> name;
    private JsonNullable<String> lastName;
    private JsonNullable<String> email;
   // private JsonNullable<Long> phoneNumber;

    public PatchUserDTO(JsonNullable<String> name, JsonNullable<String> lastName, JsonNullable<String> email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
       // this.phoneNumber = phoneNumber;
    }

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getLastName() {
        return lastName;
    }

    public void setLastName(JsonNullable<String> lastName) {
        this.lastName = lastName;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }

//    public JsonNullable<Long> getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(JsonNullable<Long> phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}