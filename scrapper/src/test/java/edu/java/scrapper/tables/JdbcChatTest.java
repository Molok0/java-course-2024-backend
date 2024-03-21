//package edu.java.scrapper.tables;
//
//import edu.java.api.model.TgChat;
//import edu.java.api.model.repository.jdbc.TgChatRepositoryImpl;
//import edu.java.scrapper.IntegrationTest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//public class JdbcChatTest extends IntegrationTest {
//
//    @Autowired
//    private TgChatRepositoryImpl chatRepository;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    @Transactional
//    @Rollback
//    void addTest() {
//        try(var conn = POSTGRES.createConnection("")) {
//
//            TgChat tgChat = new TgChat();
//            tgChat.setId(21l);
//            chatRepository.add(tgChat);
//
//            PreparedStatement select = conn.prepareStatement("SELECT * FROM CHAT");
//            var resultSet = select.executeQuery();
//
//            assertThat(!resultSet.next()).isTrue();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    void removeTest() {
//
//    }
//    @Test
//    void findAllTest(){
//        TgChat tgChat = new TgChat();
//        TgChat tgChat2 = new TgChat();
//        tgChat.setId(1l);
//        tgChat2.setId(3l);
//        chatRepository.add(tgChat);
//        chatRepository.add(tgChat2);
//        var chats = chatRepository.findAll();
//        assertThat(1l).isEqualTo(chats.get(0).getId());
//        assertThat(3l).isEqualTo(chats.get(1).getId());
//    }
//}
