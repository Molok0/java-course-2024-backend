package edu.java.api.model.repository.jdbc;

import edu.java.api.model.TgChatUrl;
import edu.java.api.model.mapper.TgChatUrlMapper;
import edu.java.api.model.repository.interfaces.TgChatUrlRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
public class JdbcTgChatUrlRepositoryImpl implements TgChatUrlRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTgChatUrlRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(TgChatUrl tgChatUrl) {
        log.info(String.valueOf(tgChatUrl.getTgChatId()));
        jdbcTemplate.update(
            "INSERT INTO CHAT_URL (url_id, chat_id) VALUES (?, ?)",
            tgChatUrl.getUrlId(),
            tgChatUrl.getTgChatId()
        );
    }

    @Override
    @Transactional
    public void remove(TgChatUrl tgChatUrl) {

        var res = jdbcTemplate.update(
            "DELETE FROM CHAT_URL WHERE url_id=? AND chat_id=?",
            tgChatUrl.getUrlId(),
            tgChatUrl.getTgChatId()
        );
        log.info("Кол-во удалений: " + res);
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

    @Override
    public List<Long> findByUrlId(Long id) {
        return jdbcTemplate.query(
            "SELECT chat_id FROM CHAT_URL WHERE url_id=?",
            (rs, rowNum) -> rs.getLong("chat_id"),
            id
        );
    }

}