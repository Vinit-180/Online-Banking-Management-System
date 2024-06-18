package com.example.Online.Banking.Management.System.Controllers;


import com.example.Online.Banking.Management.System.ApiManager;
import com.example.Online.Banking.Management.System.models.User;
import com.example.Online.Banking.Management.System.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService service;

    @PostMapping("/adduser")
    public ApiManager<User> addUser(@RequestBody User u){
        return service.saveUser(u);
    }

    @GetMapping("/getuser/{id}")
    public ApiManager<User>getUserById(@PathVariable(value = "id")long id){
        return service.findUserbyId(id);
    }


    @GetMapping("/getallusers")
    public ApiManager<List<User>> listUser(){
        System.out.println("List All Users");
        return service.findAllUsers();
    }

    @PutMapping("/updateuser/{id}")
    public ApiManager<User> updateUser(@PathVariable(value = "id")long id,@RequestBody User details){
        return service.updateUserById(id,details);
    }

    @PostMapping("/signin")
    public ApiManager<User> signInUser(@RequestBody Map<String, String> credentials){
        return service.userSignIn(credentials.get("username"),credentials.get("password"));
    }

}
