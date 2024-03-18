package edu.java.api.model.repository.jdbc;

import edu.java.api.model.Url;
import edu.java.api.model.mapper.UrlMapper;
import edu.java.api.model.repository.interfaces.UrlRepository;
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
    public void add(Url url) {
        jdbcTemplate.update("INSERT INTO URL VALUES (?, ?, ?)", url.getId(), url.getUrl(), url.getLastCheck());
    }

    @Override
    @Transactional
    public void remove(Url url) {
        jdbcTemplate.update("DELETE FROM URL WHERE id=?", url.getId());
    }

    @Override
    public List<Url> findAll() {
        return jdbcTemplate.query("select * from URL", new UrlMapper());
    }
}
