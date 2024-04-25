package edu.java.api.model.repository.interfaces;

import edu.java.api.model.Url;
import edu.java.exception.JdbcDatabaseException;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

public interface UrlRepository {
    void add(String url) throws JdbcDatabaseException;

    void remove(String url);

    List<Url> findAll();

    Long getId(String url);

    String findById(Long id);

    List<Url> findByLastCheckTime(OffsetDateTime OffsetDateTimeMinusHours);

    void updateByLastCheckTime(OffsetDateTime localTimeMinusHours);

    int updateByTimeUrl(Long id, String time);
}
