package com.demo.LibroSync.dao.impl;

import com.demo.LibroSync.dao.AuthorDao;
import com.demo.LibroSync.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int create(Author author) {
        String sql = "INSERT INTO authors (name,age,country) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, author.getName(), author.getAge(), author.getCountry());
    }

}
