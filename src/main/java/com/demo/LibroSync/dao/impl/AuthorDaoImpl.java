package com.demo.LibroSync.dao.impl;

import com.demo.LibroSync.dao.AuthorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;
}
