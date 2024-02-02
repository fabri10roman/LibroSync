package com.demo.LibroSync.dao;

import com.demo.LibroSync.domain.Author;

import java.util.List;

public interface AuthorDao {

    int create(Author author);
    List<Author> getAll();

}
