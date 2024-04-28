package edu.java.domain.mapper;

import edu.java.domain.TgChatUrl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TgChatUrlMapper implements RowMapper {
    private static final String URL_ID = "url_id";
    private static final String CHAT_ID = "chat_id";

    @Override
    public TgChatUrl mapRow(ResultSet rs, int rowNum) throws SQLException {
        TgChatUrl tgChatUrl = new TgChatUrl();
        tgChatUrl.setUrlId(rs.getLong(URL_ID));
        tgChatUrl.setTgChatId(rs.getLong(CHAT_ID));
        return tgChatUrl;
    }
}
