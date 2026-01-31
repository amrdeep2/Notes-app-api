package com.example.demo.repository;

import com.example.demo.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositroy extends JpaRepository<user, Integer> {
    user findByEmail(String email);

    boolean existsByEmail(String email);
}
