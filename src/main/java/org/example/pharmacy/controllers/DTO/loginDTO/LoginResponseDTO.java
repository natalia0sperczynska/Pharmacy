package org.example.pharmacy.controllers.DTO.loginDTO;

public class LoginResponseDTO {
    private String token;
    private long userId;

    public String getToken() {
        return token;
    }
    public long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDTO(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}
