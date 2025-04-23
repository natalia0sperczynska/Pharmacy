package org.example.pharmacy.services.authService;

import jakarta.transaction.Transactional;
import org.example.pharmacy.controllers.DTO.loginDTO.LoginDTO;
import org.example.pharmacy.controllers.DTO.loginDTO.LoginResponseDTO;
import org.example.pharmacy.controllers.DTO.registerDTO.RegisterDTO;
import org.example.pharmacy.controllers.DTO.registerDTO.RegisterResponseDTO;
import org.example.pharmacy.persistance.entities.AuthEntity;
import org.example.pharmacy.persistance.entities.UserEntity;
import org.example.pharmacy.persistance.repository.AuthRepository;
import org.example.pharmacy.persistance.repository.UserRepository;
import org.example.pharmacy.services.authService.error.*;
import org.example.pharmacy.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional
    public RegisterResponseDTO register(RegisterDTO registerDTO) throws UserAlreadyExistsException{
        Optional<AuthEntity> existingAuth = authRepository.findByUsername(registerDTO.getUsername());

        if(existingAuth.isPresent()){
            throw UserAlreadyExistsException.create(registerDTO.getUsername());
        }
        UserEntity newUser = new UserEntity();
        newUser.setEmail(registerDTO.getEmail());
        userRepository.save(newUser);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        authEntity.setUsername(registerDTO.getUsername());
        validateRole(registerDTO.getRole());
        authEntity.setRole(registerDTO.getRole());
        authEntity.setUser(newUser);

        authRepository.save(authEntity);

        return new RegisterResponseDTO(newUser.getId(), authEntity.getUsername(),authEntity.getRole());

    }
    public LoginResponseDTO login(LoginDTO loginDTO){
        AuthEntity authEntity = authRepository.findByUsername(loginDTO.getUsername()).orElseThrow(()-> UserNotFoundException.create(loginDTO.getUsername()));

        if(!passwordEncoder.matches(loginDTO.getPassword(),authEntity.getPassword())){
            throw IncorrectPasswordException.create();
        }
        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDTO(token);
    }

    private void validateRole(Role roleInput){
        if(roleInput == null || (!roleInput.equals(Role.ROLE_USER) && !roleInput.equals(Role.ROLE_ADMIN))){
            throw InvalidRoleException.create(roleInput != null ? roleInput.name() : "null");
        }
    }
}
