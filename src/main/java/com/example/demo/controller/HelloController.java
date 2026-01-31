package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class HelloController {

    private final UserService service;

    public HelloController(UserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "🔥 Hello from Spring Boot!";
    }

    @GetMapping("/")
    public String home() {
        return "🏠 Welcome to the Spring Boot API!";
    }

    @PostMapping("/users")
    public userResponseDto createUser(@Valid @RequestBody userRequestDto user) {
       return service.addUser(user);
    }

    @GetMapping("/users")
    public List<userResponseDto> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public userResponseDto getUser(@PathVariable int id) {
        return service.getUser(id);
    }

    @PutMapping("/users/{id}")
    public userResponseDto updateUser(@PathVariable int id, @RequestBody UpdateUserRequestDto user) {
        return service.getUser(id);
   }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        boolean deleted = service.deleteUser(id);
        if (deleted) {
            return "User deleted successfully!";
        } else {
            return "User not found!";
        }
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return service.login(dto);
    }

    @GetMapping("/private")
    public String privateRoute() {
        return "you are authenticated";
    }

}