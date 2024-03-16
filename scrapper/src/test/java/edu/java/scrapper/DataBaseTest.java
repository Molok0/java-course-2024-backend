package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DataBaseTest extends IntegrationTest{
    @Test
    public void testContainerStartup() {
        assertThat(POSTGRES.isRunning()).isEqualTo(true);
    }

    @Test
    public void testContainerCreateColumIdInURL() {
        try(var conn = POSTGRES.createConnection("")) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM URL");
            String result = preparedStatement.executeQuery().getMetaData().getColumnName(1);
            assertThat(result).isEqualTo("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testContainerCreateColumUrlInURL() {
        try(var conn = POSTGRES.createConnection("")) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM URL");
            String result = preparedStatement.executeQuery().getMetaData().getColumnName(2);
            assertThat(result).isEqualTo("url");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testContainerCreateColumIdInCHAT() {
        try(var conn = POSTGRES.createConnection("")) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM CHAT");
            String result = preparedStatement.executeQuery().getMetaData().getColumnName(1);
            assertThat(result).isEqualTo("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
