package org.example.pharmacy.controllers.DTO.loginDTO;

public class LoginResponseDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}
