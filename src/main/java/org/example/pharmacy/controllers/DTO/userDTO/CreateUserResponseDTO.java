package org.example.pharmacy.controllers.DTO.userDTO;

public class CreateUserResponseDTO {
    private long id;
    private String username;

    public CreateUserResponseDTO(long id, String username) {
        this.id = id;
        this.username = username;
    }
}
