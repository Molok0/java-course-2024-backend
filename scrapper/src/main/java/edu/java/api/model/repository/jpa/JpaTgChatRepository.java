package edu.java.api.model.repository.jpa;

import edu.java.api.model.entity.TgChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTgChatRepository extends JpaRepository<TgChatEntity, Long> {

    @Modifying
    @Query("DELETE FROM TgChatEntity WHERE id = :id")
    void remove(@Param("id") Long id);

}
