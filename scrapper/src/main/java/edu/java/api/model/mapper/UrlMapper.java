package edu.java.api.model.mapper;

import edu.java.api.model.Url;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UrlMapper implements RowMapper {
    @Override
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setId(rs.getLong("id"));
        url.setUrl(rs.getString("url"));
        url.setLastCheck(rs.getString("last_check"));
        return url;
    }
}
