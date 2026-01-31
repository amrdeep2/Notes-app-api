package com.example.demo.dto;

import jakarta.persistence.Id;

public class userResponseDto {

    private int id;

    private String name;
    private String email;

    public userResponseDto(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;



    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

