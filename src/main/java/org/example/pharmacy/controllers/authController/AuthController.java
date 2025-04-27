package org.example.pharmacy.controllers.authController;

import jakarta.validation.Valid;
import org.example.pharmacy.controllers.DTO.loginDTO.LoginDTO;
import org.example.pharmacy.controllers.DTO.loginDTO.LoginResponseDTO;
import org.example.pharmacy.controllers.DTO.registerDTO.RegisterDTO;
import org.example.pharmacy.controllers.DTO.registerDTO.RegisterResponseDTO;
import org.example.pharmacy.services.authService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//update data

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponseDTO>register(@Valid @RequestBody RegisterDTO requestBody){
       RegisterResponseDTO registerDTO = authService.register(requestBody);
       return new ResponseEntity<RegisterResponseDTO>(registerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO requestBody){
       LoginResponseDTO loginResponseDTO= authService.login(requestBody);
       return new ResponseEntity<>(loginResponseDTO,HttpStatus.CREATED);
    }
}
