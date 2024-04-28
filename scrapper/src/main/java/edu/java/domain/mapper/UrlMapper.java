package edu.java.domain.mapper;

import edu.java.domain.Url;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UrlMapper implements RowMapper {
    private static final String ID = "id";
    private static final String URL = "url";
    private static final String LAST_CHECK = "last_check";
    private static final String LAST_CHANGE = "last_change";

    @Override
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setId(rs.getLong(ID));
        url.setUrl(rs.getString(URL));
        url.setLastCheck(rs.getString(LAST_CHECK));
        url.setLastChange(rs.getString(LAST_CHANGE));
        return url;
    }
}
