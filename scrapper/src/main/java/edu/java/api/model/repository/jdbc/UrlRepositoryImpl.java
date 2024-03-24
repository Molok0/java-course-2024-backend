package edu.java.api.model.repository.jdbc;

import edu.java.api.model.Url;
import edu.java.api.model.mapper.UrlMapper;
import edu.java.api.model.repository.interfaces.UrlRepository;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UrlRepositoryImpl implements UrlRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UrlRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(String url) {
        jdbcTemplate.update("INSERT INTO URL VALUES (?, ?, ?)", url, LocalTime.now());
    }

    @Override
    @Transactional
    public void remove(String url) {
        jdbcTemplate.update("DELETE FROM URL WHERE url=?", url);
    }

    @Override
    public List<Url> findAll() {
        return jdbcTemplate.query("select * from URL", new UrlMapper());
    }

    @Override
    public Long getId(String url) {
        return jdbcTemplate.queryForObject("SELECT id FROM URL WHERE url=?", Long.class, url);
    }
}
