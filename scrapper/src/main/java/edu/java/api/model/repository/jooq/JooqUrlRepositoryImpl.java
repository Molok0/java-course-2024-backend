package edu.java.api.model.repository.jooq;

import edu.java.api.model.Url;
import edu.java.api.model.repository.interfaces.UrlRepository;
import edu.java.exception.JdbcDatabaseException;
import java.time.OffsetDateTime;
import java.util.List;

public class JooqUrlRepositoryImpl implements UrlRepository {
    @Override
    public void add(String url) throws JdbcDatabaseException {

    }

    @Override
    public void remove(String url) {

    }

    @Override
    public List<Url> findAll() {
        return null;
    }

    @Override
    public Long getId(String url) {
        return null;
    }

    @Override
    public String findById(Long id) {
        return null;
    }

    @Override
    public List<Url> findByLastCheckTime(OffsetDateTime offsetDateTimeMinusHours) {
        return null;
    }

    @Override
    public void updateByLastCheckTime(OffsetDateTime offsetDateTimeMinusHours) {

    }

    @Override
    public int updateByTimeUrl(Long id, OffsetDateTime time) {
        return 0;
    }
}
