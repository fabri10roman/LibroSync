package com.demo.LibroSync.controller;

import com.demo.LibroSync.domain.Author;
import com.demo.LibroSync.dto.ResponsePayload;
import com.demo.LibroSync.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponsePayload create(@RequestBody Author author) {
        return authorService.create(author);
    }


}