package com.example.demo.repository;

import com.example.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByUser_Email(String email);
    Optional<Note> findByIdAndUser_Email(Integer id, String email);
}
