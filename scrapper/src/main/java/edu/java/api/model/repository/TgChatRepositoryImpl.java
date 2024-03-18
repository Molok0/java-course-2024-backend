package edu.java.api.model.repository;

import edu.java.api.model.TgChat;
import java.util.List;
import edu.java.api.model.mapper.TgChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TgChatRepositoryImpl implements TgChatRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TgChatRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(TgChat tgChat) {
        jdbcTemplate.update("INSERT INTO CHAT VALUES (?)", tgChat.getId());
    }

    @Override
    public void remove(TgChat tgChat) {
        jdbcTemplate.update("DELETE FROM CHAT WHERE id=?", tgChat.getId());
    }

    @Override
    public List<TgChat> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAT", new TgChatMapper());
    }
}
