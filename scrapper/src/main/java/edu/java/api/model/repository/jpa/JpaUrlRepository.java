package edu.java.api.model.repository.jpa;

import edu.java.api.model.entity.UrlEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUrlRepository extends JpaRepository<UrlEntity, Long> {

    @Modifying
    @Query("DELETE FROM UrlEntity WHERE url = :url")
    void remove(@Param("url") String url);

    List<UrlEntity> findAll();

    @Query("SELECT id FROM UrlEntity WHERE url = :url")
    Long findIdByUrl(@Param("url") String url);

}
