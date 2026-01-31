package com.example.demo.controller;

import com.example.demo.dto.NoteRequestDto;
import com.example.demo.dto.NoteResponseDto;
import com.example.demo.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotesController {

    private final NoteService service;

    public NotesController(NoteService service) {
        this.service = service;
    }

    @PostMapping("/notes")
    public NoteResponseDto create(@Valid @RequestBody NoteRequestDto dto, Authentication auth) {
        return service.create(dto, auth.getName());
    }

    @GetMapping("/notes")
    public List<NoteResponseDto> myNotes(Authentication auth) {
        return service.myNotes(auth.getName());
    }

    @GetMapping("/notes/{id}")
    public NoteResponseDto getOne(@PathVariable Integer id, Authentication auth) {
        return service.getOne(id, auth.getName());
    }

    @PutMapping("/notes/{id}")
    public NoteResponseDto update(@PathVariable Integer id,
                                  @Valid @RequestBody NoteRequestDto dto,
                                  Authentication auth) {
        return service.update(id, dto, auth.getName());
    }

    @DeleteMapping("/notes/{id}")
    public String delete(@PathVariable Integer id, Authentication auth) {
        return service.delete(id, auth.getName()) ? "deleted" : "not found";
    }
}
