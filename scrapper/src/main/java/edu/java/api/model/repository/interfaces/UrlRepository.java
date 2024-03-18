package edu.java.api.model.repository.interfaces;

import edu.java.api.model.Url;
import java.util.List;

public interface UrlRepository {
    void add(Url url);

    void remove(Url url);

    List<Url> findAll();
}
