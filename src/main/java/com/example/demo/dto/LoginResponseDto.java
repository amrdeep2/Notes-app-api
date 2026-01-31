package com.example.demo.dto;

public class LoginResponseDto {

    private int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    private String name;
    private String email;
    private String token;

    public LoginResponseDto(int id, String name, String email, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    // getters
}
