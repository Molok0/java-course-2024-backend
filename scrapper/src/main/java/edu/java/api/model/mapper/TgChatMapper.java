package edu.java.api.model.mapper;

import edu.java.api.model.TgChat;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TgChatMapper implements RowMapper {
    private static final String ID = "id";

    @Override
    public TgChat mapRow(ResultSet rs, int rowNum) throws SQLException {
        TgChat tgChat = new TgChat();
        tgChat.setId(rs.getLong(ID));
        return tgChat;
    }
}
