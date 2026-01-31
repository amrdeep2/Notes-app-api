package com.example.demo.service;

import com.example.demo.dto.NoteRequestDto;
import com.example.demo.dto.NoteResponseDto;
import com.example.demo.model.Note;
import com.example.demo.model.user;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepositroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepo;
    private final UserRepositroy userRepo;

    public NoteService(NoteRepository noteRepo, UserRepositroy userRepo) {
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }

    public NoteResponseDto create(NoteRequestDto dto, String email) {
        user u = userRepo.findByEmail(email);
        if (u == null) return null;

        Note n = new Note();
        n.setTitle(dto.title());
        n.setContent(dto.content());
        n.setUser(u);

        Note saved = noteRepo.save(n);

        return new NoteResponseDto(saved.getId(), saved.getTitle(), saved.getContent());
    }

    public List<NoteResponseDto> myNotes(String email) {
        return noteRepo.findByUser_Email(email).stream()
                .map(n -> new NoteResponseDto(n.getId(), n.getTitle(), n.getContent()))
                .toList();
    }

    public NoteResponseDto getOne(Integer id, String email) {
        return noteRepo.findByIdAndUser_Email(id, email)
                .map(n -> new NoteResponseDto(n.getId(), n.getTitle(), n.getContent()))
                .orElse(null);
    }

    public NoteResponseDto update(Integer id, NoteRequestDto dto, String email) {
        return noteRepo.findByIdAndUser_Email(id, email).map(n -> {
            n.setTitle(dto.title());
            n.setContent(dto.content());
            Note saved = noteRepo.save(n);
            return new NoteResponseDto(saved.getId(), saved.getTitle(), saved.getContent());
        }).orElse(null);
    }

    public boolean delete(Integer id, String email) {
        var noteOpt = noteRepo.findByIdAndUser_Email(id, email);
        if (noteOpt.isEmpty()) return false;
        noteRepo.delete(noteOpt.get());
        return true;
    }
}
