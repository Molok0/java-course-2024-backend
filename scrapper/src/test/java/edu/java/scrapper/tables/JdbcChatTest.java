package edu.java.scrapper.tables;

import edu.java.api.model.repository.jdbc.JdbcTgChatRepositoryImpl;
import edu.java.exception.JdbcDatabaseException;
import edu.java.scrapper.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class JdbcChatTest extends IntegrationTest {

    @Autowired
    private JdbcTgChatRepositoryImpl jdbcTgChatRepository;

    @Test
    @Transactional
    @Rollback
    void doubleAddTest() {
        Long one = 1L;
        jdbcTgChatRepository.add(one);

        assertThatThrownBy(() -> {
            jdbcTgChatRepository.add(one);
        }).isInstanceOf(JdbcDatabaseException.class);
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        assertThatThrownBy(() -> {
            jdbcTgChatRepository.remove(2L);
        }).isInstanceOf(JdbcDatabaseException.class);
    }

}
