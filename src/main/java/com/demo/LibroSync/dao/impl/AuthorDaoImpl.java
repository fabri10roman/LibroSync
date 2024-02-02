package com.demo.LibroSync.dao.impl;

import com.demo.LibroSync.dao.AuthorDao;
import com.demo.LibroSync.domain.Author;
import com.demo.LibroSync.mapper.AuthorRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int create(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO authors (name,age,country) VALUES (?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setInt(2, author.getAge());
            ps.setString(3, author.getCountry());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            return 0;
        }
        return ((Number) keys.get("id")).intValue();
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
    public int partialUpdate(Author author) {
        String sql = "UPDATE authors SET name = ?, age = ?, country = ? WHERE id = ?";
        return jdbcTemplate.update(sql, author.getName(), author.getAge(), author.getCountry(), author.getId());
    }

    @Override
    public int update(Author author) {
        String sql = "UPDATE authors SET name = ?, age = ?, country = ? WHERE id = ?";
        return jdbcTemplate.update(sql, author.getName(), author.getAge(), author.getCountry(), author.getId());
    }

}
