package com.example.springboottestdrivenexample.controller;

import com.example.springboottestdrivenexample.model.User;
import com.example.springboottestdrivenexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return null;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUserById(id);
    }
}
