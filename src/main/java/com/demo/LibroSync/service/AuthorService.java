package com.demo.LibroSync.service;

import com.demo.LibroSync.dao.AuthorDao;
import com.demo.LibroSync.domain.Author;
import com.demo.LibroSync.dto.ResponsePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDao authorDao;

    public ResponsePayload create(Author author) {
        int result = authorDao.create(author);
        if (result == 1) {
            List<Object> data = List.of(author);
            return ResponsePayload.builder()
                    .message("Author created successfully")
                    .data(data)
                    .build();
        }
        throw new IllegalStateException("Failed to create author");
    }

}
