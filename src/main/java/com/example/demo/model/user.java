package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email(message = "email cannot be empty")
    private String email;
    @NotBlank(message = "name cannot be empty")
    private String name;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @NotBlank
private String pass;

   public user (){}
    public user( String email,String name,String pass){
        this.name= name;
        this.pass =pass;
        this.email=email;

    }
    public int getId(){return id;}
    public String getEmail(){return email;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public void setEmail(String email){this.email = email;}





}
