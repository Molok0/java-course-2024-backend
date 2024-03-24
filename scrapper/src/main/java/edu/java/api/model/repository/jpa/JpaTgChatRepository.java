package edu.java.api.model.repository.jpa;

import edu.java.api.model.entity.TgChatEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface JpaTgChatRepository extends JpaRepository<TgChatEntity, Long> {

    @Transactional
    @Modifying
    @Query("INSERT INTO CHAT (id) VALUES (:id)")
    void add(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM CHAT WHERE id = :id")
    void remove(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("SELECT * FROM CHAT")
    List<TgChatEntity> findAll();

}
