package org.example.pharmacy.services.userService;

import org.example.pharmacy.controllers.DTO.userDTO.CreateUserResponseDTO;
import org.example.pharmacy.controllers.DTO.userDTO.GetUserDTO;
import org.example.pharmacy.persistance.entities.AuthEntity;
import org.example.pharmacy.persistance.entities.UserEntity;
import org.example.pharmacy.persistance.repository.AuthRepository;
import org.example.pharmacy.persistance.repository.UserRepository;
import org.example.pharmacy.services.authService.AuthService;
import org.example.pharmacy.services.purchaseService.error.UserNotFoundException;
import org.example.pharmacy.services.userService.error.UserWithThisUsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final AuthRepository authRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthService authService, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.authRepository = authRepository;
    }

    public GetUserDTO getOneUser(long id){
        var user = userRepository.findById(id).orElseThrow(()-> UserNotFoundException.create(id));
        return new GetUserDTO(user.getId(),user.getName(),user.getLastName(), user.getEmail());
    }

    public CreateUserResponseDTO create(UserEntity userEntity){
        var user = userRepository.save(userEntity);
        return new CreateUserResponseDTO(user.getId(), user.getName());
    }
    public GetUserDTO getUserByUsername(String username){
        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(()-> UserWithThisUsernameNotFoundException.create(username));
        UserEntity user = authEntity.getUser();
        return new GetUserDTO(user.getId(),user.getName(),user.getLastName(), user.getEmail());
    }
}

