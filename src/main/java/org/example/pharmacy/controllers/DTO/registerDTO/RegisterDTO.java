package org.example.pharmacy.controllers.DTO.registerDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.pharmacy.services.valuedObject.ValidPassword;
import org.example.pharmacy.types.Role;

public class RegisterDTO {
    @NotBlank(message = "Password is required")
    @ValidPassword
    private String password;

    @NotBlank(message = "Username is required")
    private String username;

    private Role role;
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    public RegisterDTO(String password, String username, Role role, String email) {
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
