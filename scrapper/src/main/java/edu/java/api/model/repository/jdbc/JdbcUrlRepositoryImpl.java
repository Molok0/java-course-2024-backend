package edu.java.api.model.repository.jdbc;

import edu.java.api.model.Url;
import edu.java.api.model.mapper.UrlMapper;
import edu.java.api.model.repository.interfaces.UrlRepository;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
public class JdbcUrlRepositoryImpl implements UrlRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUrlRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(String url) {
        jdbcTemplate.update(
            "INSERT INTO url (url, last_check, last_change) VALUES (?, ?, ?)",
            url,
            LocalTime.now(),
            OffsetDateTime.MIN
        );
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
        // Переписать на Option
        try {
            return jdbcTemplate.queryForObject("SELECT id FROM URL WHERE url=?", Long.class, url);
        } catch (IncorrectResultSizeDataAccessException exception) {
            return null;
        }

    }

    @Override
    public String findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT url FROM URL WHERE id=?", String.class, id);
    }

    @Override
    @Transactional
    public List<Url> findByLastCheckTime(LocalTime localTimeMinusHours) {
        updateByLastCheckTime(localTimeMinusHours);
        return jdbcTemplate.query("SELECT * FROM URL WHERE last_check < ?", new UrlMapper(), localTimeMinusHours);
    }

    @Override
    @Transactional
    public void updateByLastCheckTime(LocalTime localTimeMinusHours) {
        jdbcTemplate.update(
            "UPDATE URL SET last_check = ? WHERE last_check < ?",
            LocalTime.now(),
            localTimeMinusHours
        );
    }

    @Override
    @Transactional
    public int updateByTimeUrl(Long id, String time) {
        return jdbcTemplate.update(
            "UPDATE URL SET last_change=? WHERE id=?",
            OffsetDateTime.parse(time),
            id
        );
    }
}
