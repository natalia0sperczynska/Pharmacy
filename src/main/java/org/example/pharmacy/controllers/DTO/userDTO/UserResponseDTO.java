package org.example.pharmacy.controllers.DTO.userDTO;

public class UserResponseDTO {
    private long id;
    private String username;

    public UserResponseDTO(long id, String username) {
        this.id = id;
        this.username = username;
    }

}
