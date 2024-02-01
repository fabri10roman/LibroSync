package com.demo.LibroSync.dao;

import com.demo.LibroSync.domain.Author;
import com.demo.LibroSync.dto.ResponsePayload;

public interface AuthorDao {

    int create(Author author);

}
