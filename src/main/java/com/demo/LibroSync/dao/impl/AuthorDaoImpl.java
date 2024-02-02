package com.demo.LibroSync.dao.impl;

import com.demo.LibroSync.dao.AuthorDao;
import com.demo.LibroSync.domain.Author;
import com.demo.LibroSync.mapper.AuthorRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int create(Author author) {
        String sql = "INSERT INTO authors (name,age,country) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, author.getName(), author.getAge(), author.getCountry());
    }

    @Override
    public List<Author> getAll() {
        String sql = "SELECT * FROM authors LIMIT 100";
        return jdbcTemplate.query(sql, new AuthorRowMapper());
    }

    @Override
    public Optional<Author> getById(Integer id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        List<Author> authors = jdbcTemplate.query(sql, new AuthorRowMapper(), id);
        return authors.stream().findFirst();
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Author author) {
        String sql = "UPDATE authors SET name = ?, age = ?, country = ? WHERE id = ?";
        return jdbcTemplate.update(sql, author.getName(), author.getAge(), author.getCountry(), author.getId());
    }

}
