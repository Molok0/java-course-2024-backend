package edu.java.api.model.repository.jpa;

import edu.java.api.model.entity.UrlEntity;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface JpaUrlChatRepository extends JpaRepository<UrlEntity, Long> {

    @Transactional
    @Modifying
    @Query("INSERT INTO URL VALUES (:url, :createdAt)")
    void add(@Param("url") String url, @Param("last_check") LocalTime createdAt);

    @Transactional
    @Modifying
    @Query("DELETE FROM URL WHERE url = :url")
    void remove(@Param("url") String url);

    List<UrlEntity> findAll();

    @Transactional
    @Query("SELECT id FROM UrlEntity WHERE url = :url")
    Long findIdByUrl(@Param("url") String url);

}
