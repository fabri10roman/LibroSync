package com.demo.LibroSync.dao;

import com.demo.LibroSync.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    int create(Book book);
    List<Book> getAll();
    Optional<Book> getByIsbn(String isbn);
    int deleteById(String isbn);
    int partialUpdate(String isbn, Book book);
    int update (String isbn,Book book);
}
