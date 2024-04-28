package edu.java.domain.repository.jooq;

import edu.java.domain.TgChatUrl;
import edu.java.domain.repository.interfaces.TgChatUrlRepository;
import java.util.List;

public class JooqTgChatUrlRepositoryImpl implements TgChatUrlRepository {
    @Override
    public void add(TgChatUrl tgChatUrl) {

    }

    @Override
    public void remove(TgChatUrl tgChatUrl) {

    }

    @Override
    public List<TgChatUrl> findAll() {
        return null;
    }

    @Override
    public List<Long> findByTgChatId(Long id) {
        return null;
    }

    @Override
    public List<Long> findByUrlId(Long id) {
        return null;
    }
}
