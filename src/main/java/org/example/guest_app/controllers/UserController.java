package org.example.guest_app.controllers;

import org.example.guest_app.entities.User;
import org.example.guest_app.repositories.UserRepository;
import org.example.guest_app.requests.UserCreateRequest;
import org.example.guest_app.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping
    public User save(@RequestBody UserCreateRequest userCreateRequest){
        return userService.saveUser(userCreateRequest);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        //custom exception eklenecek
        return userService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId,@RequestBody User newUser){
        return userService.updateService(userId,newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }





}
