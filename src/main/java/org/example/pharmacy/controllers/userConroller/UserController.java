package org.example.pharmacy.controllers.userConroller;

import org.example.pharmacy.controllers.DTO.userDTO.CreateUserResponseDTO;
import org.example.pharmacy.controllers.DTO.userDTO.GetUserDTO;
import org.example.pharmacy.controllers.DTO.userDTO.UserResponseDTO;
import org.example.pharmacy.services.userService.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String loginUser(){
        //create login dto, create login service with user verification with hashed token, return user login dto with token
        return "token";
    }
    @PostMapping("/{id}")
    public GetUserDTO getUser(@PathVariable long id){
        return userService.getOneUser(id);
    }

//    @PostMapping()
//    public CreateUserResponseDTO createUser(RequestBody CreateUserRequestBody user){
//        return new userService.create(user);
//    }
    @PostMapping("/me")
    public UserResponseDTO getMe(Principal principal){
        return new UserResponseDTO(1, principal.getName());
    }

}
