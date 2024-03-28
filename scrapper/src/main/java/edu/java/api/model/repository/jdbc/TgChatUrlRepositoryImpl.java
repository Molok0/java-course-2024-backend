package edu.java.api.model.repository.jdbc;

import edu.java.api.model.TgChatUrl;
import edu.java.api.model.mapper.TgChatUrlMapper;
import edu.java.api.model.repository.interfaces.TgChatUrlRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TgChatUrlRepositoryImpl implements TgChatUrlRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TgChatUrlRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(TgChatUrl tgChatUrl) {
        jdbcTemplate.update("INSERT INTO CHAT_URL VALUES (?, ?)", tgChatUrl.getUrlId(), tgChatUrl.getTgChatId());
    }

    @Override
    @Transactional
    public void remove(TgChatUrl tgChatUrl) {
        jdbcTemplate.update(
            "DELETE FROM CHAT_URL WHERE url_id=? AND chat_id=?",
            tgChatUrl.getUrlId(),
            tgChatUrl.getTgChatId()
        );
    }

    @Override
    public List<TgChatUrl> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAT_URL", new TgChatUrlMapper());
    }

    @Override
    public List<Long> findByTgChatId(Long id) {
        return jdbcTemplate.query(
            "SELECT url_id FROM CHAT_URL WHERE chat_id=?",
            (rs, rowNum) -> rs.getLong("url_id"),
            id
        );
    }

}
