package edu.java.api.model.repository.jdbc;

import edu.java.api.model.TgChat;
import edu.java.api.model.mapper.TgChatMapper;
import edu.java.api.model.repository.interfaces.TgChatRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcTgChatRepositoryImpl implements TgChatRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTgChatRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(Long id) {
        jdbcTemplate.update("INSERT INTO CHAT VALUES (?)", id);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM CHAT WHERE id=?", id);
    }

    @Override
    public List<TgChat> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAT", new TgChatMapper());
    }
}
