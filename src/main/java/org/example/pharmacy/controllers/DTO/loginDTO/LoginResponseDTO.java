package org.example.pharmacy.controllers.DTO.loginDTO;

public class LoginResponseDTO {
    private String token;
    private long userId;
    private String username;

    public String getToken() {
        return token;
    }
    public long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginResponseDTO(String token, Long userId, String username) {
        this.token = token;
        this.userId = userId;
        this.username = username;

    }
}
