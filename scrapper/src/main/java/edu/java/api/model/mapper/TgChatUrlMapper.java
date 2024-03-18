package edu.java.api.model.mapper;

import edu.java.api.model.TgChatUrl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TgChatUrlMapper implements RowMapper {
    @Override
    public TgChatUrl mapRow(ResultSet rs, int rowNum) throws SQLException {
        TgChatUrl tgChatUrl = new TgChatUrl();
        tgChatUrl.setUrlId(rs.getLong("url_id"));
        tgChatUrl.setTgChatId(rs.getLong("chat_id"));
        return tgChatUrl;
    }
}
