package com.demo.LibroSync.mapper;

import com.demo.LibroSync.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Author.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .country(rs.getString("country"))
                .build();
    }
}
