package edu.java.scrapper.tables;

import edu.java.api.model.TgChat;
import edu.java.api.model.repository.jdbc.TgChatRepositoryImpl;
import edu.java.scrapper.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class JdbcChatTest extends IntegrationTest {

    @Autowired
    private TgChatRepositoryImpl chatRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        TgChat tgChat = new TgChat();
        tgChat.setId(1l);
        chatRepository.add(tgChat);
        assertThat(1l).isEqualTo(chatRepository.findAll().get(0));
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {

    }
}
