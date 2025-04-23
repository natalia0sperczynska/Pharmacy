package org.example.pharmacy.controllers.DTO.registerDTO;

import org.example.pharmacy.types.Role;

public class RegisterResponseDTO {
    private  long userId;
    private String username;
    private Role role;

    public RegisterResponseDTO(long userId, String username, Role role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
