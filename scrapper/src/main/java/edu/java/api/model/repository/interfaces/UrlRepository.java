package edu.java.api.model.repository.interfaces;

import edu.java.api.model.Url;
import java.util.List;

public interface UrlRepository {
    void add(String url);

    void remove(String url);

    List<Url> findAll();

    Long getId(String url);

    String findById(Long id);
}
