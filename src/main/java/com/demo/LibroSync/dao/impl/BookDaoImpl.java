package com.demo.LibroSync.dao.impl;

import com.demo.LibroSync.dao.BookDao;
import com.demo.LibroSync.domain.Book;
import com.demo.LibroSync.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int create(Book book) {
        String sql = "INSERT INTO books (isbn, title, genre, publisher, year, author_id) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getGenre(), book.getPublisher(), book.getYear(), book.getAuthorId());
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT * FROM books LIMIT 100";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Optional<Book> getByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        List<Book> books = jdbcTemplate.query(sql, new BookRowMapper(), isbn);
        return books.stream().findFirst();
    }

    @Override
    public int deleteById(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        return jdbcTemplate.update(sql, isbn);
    }

    @Override
    public int partialUpdate(String isbn,Book book) {
        String sql = "UPDATE books SET isbn = ?, title = ?, genre = ?, publisher = ?, year = ?, author_id = ? WHERE isbn = ?";
        return jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getGenre(), book.getPublisher(), book.getYear(), book.getAuthorId(), isbn);
    }

    @Override
    public int update(String isbn,Book book) {
        String sql = "UPDATE books SET isbn = ?, title = ?, genre = ?, publisher = ?, year = ?, author_id = ? WHERE isbn = ?";
        return jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getGenre(), book.getPublisher(), book.getYear(), book.getAuthorId(), isbn);
    }
}
