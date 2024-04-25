package edu.java.api.model.repository.jdbc;

import edu.java.api.model.TgChat;
import edu.java.api.model.mapper.TgChatMapper;
import edu.java.api.model.repository.interfaces.TgChatRepository;
import edu.java.exception.JdbcDatabaseException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
public class JdbcTgChatRepositoryImpl implements TgChatRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String ERROR_ADD_MESSAGE = "Ошибка добавления!";
    private static final String ERROR_REMOVE_MESSAGE = "Нет объекта на удаление";

    @Autowired
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
