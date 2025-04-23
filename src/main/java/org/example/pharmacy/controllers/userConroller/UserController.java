package org.example.pharmacy.controllers.userConroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable long id){
        //create user dto, user service, create user enityt, cerate user repository, return user from db with id
        return "User";
    }

    @PostMapping()
    public String createUser(){
        //create user dto, create user in service, validate data, hash the password, save hasshed password
        return "User created";
    }
    @PostMapping("/login")
    public String loginUser(){
        //create login dto, create login service with user verification with hashed token, return user login dto with token
        return "token";
    }

}
