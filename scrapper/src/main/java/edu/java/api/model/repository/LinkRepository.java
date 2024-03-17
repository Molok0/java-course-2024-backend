package edu.java.api.model.repository;

import edu.java.api.model.Url;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository {
    void add(Url url);

    void remove(Url url);

    List<Url> findAll();
}
