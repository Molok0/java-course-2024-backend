package edu.java.domain.repository.jdbc;

import edu.java.domain.Url;
import edu.java.domain.exception.JdbcDatabaseException;
import edu.java.domain.mapper.UrlMapper;
import edu.java.domain.repository.interfaces.UrlRepository;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class JdbcUrlRepositoryImpl implements UrlRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String ERROR_MESSAGE = "Ошибка добавления!";

    public JdbcUrlRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(String url) {
        try {
            jdbcTemplate.update(
                "INSERT INTO url (url, last_check, last_change) VALUES (?, ?, ?)",
                url,
                OffsetDateTime.now(),
                OffsetDateTime.MIN
            );
        } catch (Exception e) {
            throw new JdbcDatabaseException(ERROR_MESSAGE);
        }

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
    public List<Url> findByLastCheckTime(OffsetDateTime offsetDateTimeMinusHours) {
        log.info(String.valueOf("Сравниваем с : " + offsetDateTimeMinusHours));
        var urls =
            jdbcTemplate.query("SELECT * FROM URL WHERE last_check < ?", new UrlMapper(), offsetDateTimeMinusHours);
        updateByLastCheckTime(offsetDateTimeMinusHours);
        return urls;
    }

    @Override
    @Transactional
    public void updateByLastCheckTime(OffsetDateTime offsetDateTimeMinusHours) {
        var res = jdbcTemplate.update(
            "UPDATE URL SET last_check = ? WHERE last_check < ?",
            OffsetDateTime.now(),
            offsetDateTimeMinusHours
        );
        log.info(String.valueOf("Проверенно ссылок " + res));
    }

    @Override
    @Transactional
    public int updateByTimeUrl(Long id, OffsetDateTime time) {
        return jdbcTemplate.update(
            "UPDATE URL SET last_change=? WHERE id=?",
            time,
            id
        );
    }
}
