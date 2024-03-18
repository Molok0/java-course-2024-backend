package edu.java.scrapper.tables;

import edu.java.api.model.repository.jdbc.UrlRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class JdbcUrlTest {
    @Autowired
    private UrlRepositoryImpl urlRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    @Rollback
    void addTest() {
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
    }
}
