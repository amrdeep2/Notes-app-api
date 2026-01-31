package com.example.demo.service;
import com.example.demo.dto.*;

import com.example.demo.repository.UserRepositroy;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.model.user;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final JwtService jwtService;
    private final UserRepositroy repo;
    private final PasswordEncoder passwordEncoder;

   public UserService(UserRepositroy repo, PasswordEncoder passwordEncoder,JwtService jwtService) {
      this.repo = repo;
      this.passwordEncoder = passwordEncoder;
     this.jwtService = jwtService;
   }
    public userResponseDto addUser(@Valid userRequestDto dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user u = new user();
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());


        u.setPass(passwordEncoder.encode(dto.getPassword()));

        user saved = repo.save(u);

        return new userResponseDto(saved.getId(),saved.getName(),saved.getEmail());
    }

    public userResponseDto getUser(int id) {
        return repo.findById(id)
                .map(u -> new userResponseDto(
                        u.getId(),
                         u.getName(),
                        u.getEmail()
                ))
                .orElse(null);
    }
    public List<userResponseDto> getAllUsers() {
        return repo.findAll().stream().map(u-> new userResponseDto(
                u.getId(),
                u.getName(),
                u.getEmail()
        ))
         .collect(Collectors.toList());
    }

    public boolean deleteUser(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
     public userResponseDto updateUser(int id, UpdateUserRequestDto updatedUser) {
         return repo.findById(id).map(existUser -> {
                     existUser.setName(updatedUser.getName());
                     existUser.setEmail(updatedUser.getEmail());
                     user saved = repo.save(existUser);

                     return new userResponseDto(
                             saved.getId(),
                             saved.getName(),
                             saved.getEmail()
                     );


                 })
                 .orElse(null);
     }
    public LoginResponseDto login(LoginRequestDto dto) {

        user existing = repo.findByEmail(dto.getEmail());
        if (existing == null) return null;

        if (!passwordEncoder.matches(dto.getPassword(), existing.getPass())) return null;

        String token = jwtService.generateToken(existing.getEmail());

        return new LoginResponseDto(
                existing.getId(),
                existing.getName(),
                existing.getEmail(),
                token
        );
    }


}