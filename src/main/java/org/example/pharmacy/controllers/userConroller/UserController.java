package org.example.pharmacy.controllers.userConroller;

import org.example.pharmacy.controllers.DTO.userDTO.*;
import org.example.pharmacy.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;

    @Autowired
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
//    @GetMapping("/me")
//    public UserResponseDTO getMe(Principal p rincipal){
//        return new UserResponseDTO(1, principal.getName());
//    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDTO> getMe(Principal principal){
        GetUserDTO getUserDTO = userService.getUserByUsername(principal.getName());
        return ResponseEntity.ok(getUserDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatchResponseUserDTO> update(@PathVariable long id,@RequestBody PatchUserDTO patchUserDTO){
        PatchResponseUserDTO responseDTO=userService.updateUser(id, patchUserDTO);
        return ResponseEntity.ok(responseDTO);
    }


}
