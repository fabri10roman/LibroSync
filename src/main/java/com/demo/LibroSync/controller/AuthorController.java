package com.demo.LibroSync.controller;

import com.demo.LibroSync.domain.Author;
import com.demo.LibroSync.dto.ResponsePayload;
import com.demo.LibroSync.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponsePayload create(@RequestBody Author author) {
        return authorService.create(author);
    }
    @GetMapping
    public ResponsePayload getAll() {
        return authorService.getAll();
    }
    @GetMapping("/{id}")
    public ResponsePayload getById(@PathVariable Integer id) {
        return authorService.getById(id);
    }
    @DeleteMapping("/{id}")
    public ResponsePayload deleteById(@PathVariable Integer id) {
        return authorService.deleteById(id);
    }
    @PatchMapping("/{id}")
    public ResponsePayload partialUpdate(@RequestBody Author author, @PathVariable Integer id) {
        return authorService.partialUpdate(id,author);
    }



}
