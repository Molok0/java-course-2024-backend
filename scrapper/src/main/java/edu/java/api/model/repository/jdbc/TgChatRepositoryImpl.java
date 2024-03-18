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
public class TgChatRepositoryImpl implements TgChatRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TgChatRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(TgChat tgChat) {
        jdbcTemplate.update("INSERT INTO CHAT VALUES (?)", tgChat.getId());
    }

    @Override
    @Transactional
    public void remove(TgChat tgChat) {
        jdbcTemplate.update("DELETE FROM CHAT WHERE id=?", tgChat.getId());
    }

    @Override
    public List<TgChat> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAT", new TgChatMapper());
    }
}
