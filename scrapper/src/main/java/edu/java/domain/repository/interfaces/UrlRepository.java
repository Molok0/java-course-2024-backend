package edu.java.domain.repository.interfaces;

import edu.java.domain.Url;
import edu.java.domain.exception.JdbcDatabaseException;
import java.time.OffsetDateTime;
import java.util.List;

public interface UrlRepository {
    void add(String url) throws JdbcDatabaseException;

    void remove(String url);

    List<Url> findAll();

    Long getId(String url);

    String findById(Long id);

    List<Url> findByLastCheckTime(OffsetDateTime offsetDateTimeMinusHours);

    void updateByLastCheckTime(OffsetDateTime offsetDateTimeMinusHours);

    int updateByTimeUrl(Long id, OffsetDateTime time);
}
