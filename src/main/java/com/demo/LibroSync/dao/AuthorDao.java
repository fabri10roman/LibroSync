package com.demo.LibroSync.dao;

import com.demo.LibroSync.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    int create(Author author);
    List<Author> getAll();
    Optional<Author> getById(Integer id);
    int deleteById(Integer id);
    int partialUpdate(Author author);

}
