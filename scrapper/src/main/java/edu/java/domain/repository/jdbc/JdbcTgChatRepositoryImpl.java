package edu.java.domain.repository.jdbc;

import edu.java.domain.TgChat;
import edu.java.domain.exception.JdbcDatabaseException;
import edu.java.domain.mapper.TgChatMapper;
import edu.java.domain.repository.interfaces.TgChatRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class JdbcTgChatRepositoryImpl implements TgChatRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String ERROR_ADD_MESSAGE = "Ошибка добавления!";
    private static final String ERROR_REMOVE_MESSAGE = "Нет объекта на удаление";

    public JdbcTgChatRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(Long id) {
        try {
            jdbcTemplate.update("INSERT INTO CHAT VALUES (?)", id);
        } catch (Exception e) {
            throw new JdbcDatabaseException(ERROR_ADD_MESSAGE);
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        int a = jdbcTemplate.update("DELETE FROM CHAT WHERE id=?", id);
        if (a == 0) {
            throw new JdbcDatabaseException(ERROR_REMOVE_MESSAGE);
        }
    }

    @Override
    public List<TgChat> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAT", new TgChatMapper());
    }
}
