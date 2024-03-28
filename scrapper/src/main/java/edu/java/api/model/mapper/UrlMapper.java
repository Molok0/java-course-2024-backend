package edu.java.api.model.mapper;

import edu.java.api.model.Url;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UrlMapper implements RowMapper {
    private static final String ID = "id";
    private static final String URL = "url";
    private static final String LAST_CHECK = "last_check";

    @Override
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setId(rs.getLong(ID));
        url.setUrl(rs.getString(URL));
        url.setLastCheck(rs.getString(LAST_CHECK));
        return url;
    }
}
