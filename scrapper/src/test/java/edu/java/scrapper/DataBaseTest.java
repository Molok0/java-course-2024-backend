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
    @Test
    public void testContainerCreateColumUrlIdInCHAT_URL() {
        try(var conn = POSTGRES.createConnection("")) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM CHAT_URL");
            String result = preparedStatement.executeQuery().getMetaData().getColumnName(1);
            assertThat(result).isEqualTo("url_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testContainerCreateColumChatIdInCHAT_URL() {
        try(var conn = POSTGRES.createConnection("")) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM CHAT_URL");
            String result = preparedStatement.executeQuery().getMetaData().getColumnName(2);
            assertThat(result).isEqualTo("chat_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testContainerCreateColumLastCheckInURL() {
        try(var conn = POSTGRES.createConnection("")) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM URL");
            String result = preparedStatement.executeQuery().getMetaData().getColumnName(3);
            assertThat(result).isEqualTo("last_check");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
