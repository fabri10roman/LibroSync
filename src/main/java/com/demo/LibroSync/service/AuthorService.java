package com.demo.LibroSync.service;

import com.demo.LibroSync.dao.AuthorDao;
import com.demo.LibroSync.domain.Author;
import com.demo.LibroSync.dto.ResponsePayload;
import com.demo.LibroSync.exception.EntityNotFoundException;
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
    public ResponsePayload getAll() {
        List<Author> authors = authorDao.getAll();
        if (authors.isEmpty()) {
            throw new EntityNotFoundException("No authors found");
        }
        return ResponsePayload.builder()
                .message("Authors retrieved successfully")
                .data(List.of(authors))
                .build();
    }
    public ResponsePayload getById(Integer id) {
        Author author = authorDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author with id %d not found", id)));
        return ResponsePayload.builder()
                .message("Author retrieved successfully")
                .data(List.of(author))
                .build();
    }
    public ResponsePayload deleteById(Integer id) {
        if (authorDao.getById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format("Author with id %d not found", id));
        }
        int result = authorDao.deleteById(id);
        if (result == 1) {
            return ResponsePayload.builder()
                    .message("Author deleted successfully")
                    .build();
        }
        throw new IllegalStateException(String.format("Failed to delete author with id %d", id));
    }
    public ResponsePayload partialUpdate(Integer id,Author authorDto) {
        Author author = authorDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author with id %d not found", id)));
        if (authorDto.getName() != null) {
            author.setName(authorDto.getName());
        }
        if (authorDto.getAge() != null) {
            author.setAge(authorDto.getAge());
        }
        if (authorDto.getCountry() != null) {
            author.setCountry(authorDto.getCountry());
        }
        int result = authorDao.partialUpdate(author);
        if (result != 1) {
            throw new IllegalStateException(String.format("Failed to update author with id %d",id));
        }
        return ResponsePayload.builder()
                .message("Author updated successfully")
                .data(List.of(author))
                .build();
    }
    public ResponsePayload update (Integer id,Author author) {
        if (authorDao.getById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format("Author with id %d not found", id));
        }
        int result = authorDao.update(author);
        if (result == 1) {
            return ResponsePayload.builder()
                    .message("Author updated successfully")
                    .data(List.of(author))
                    .build();
        }
        throw new IllegalStateException(String.format("Failed to update author with id %d",author.getId()));
    }

}
