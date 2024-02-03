package com.demo.LibroSync.mapper;

import com.demo.LibroSync.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getString("isbn"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("publisher"),
                rs.getInt("year"),
                rs.getInt("author_id")
        );
    }
}
