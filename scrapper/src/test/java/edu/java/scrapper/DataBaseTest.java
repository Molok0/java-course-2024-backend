package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DataBaseTest extends IntegrationTest{
    @Test
    public void testContainerStartup() {
        try(var conn = POSTGRES.createConnection("")) {
            assertThat(POSTGRES.isRunning()).isEqualTo(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
