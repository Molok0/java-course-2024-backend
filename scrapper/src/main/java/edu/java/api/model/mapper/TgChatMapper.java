package edu.java.api.model.mapper;

import edu.java.api.model.TgChat;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TgChatMapper implements RowMapper {
    @Override
    public TgChat mapRow(ResultSet rs, int rowNum) throws SQLException {
        TgChat tgChat = new TgChat();
        tgChat.setId(rs.getLong("id"));
        return tgChat;
    }
}
