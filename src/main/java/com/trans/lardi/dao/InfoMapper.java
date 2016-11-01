package com.trans.lardi.dao;

import com.trans.lardi.db.Info;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoMapper implements RowMapper<Info> {
    public Info mapRow(ResultSet rs, int i) throws SQLException {
        Info info = new Info();

        info.setId(rs.getLong("id"));
        info.setFirstname(rs.getString("firstname"));
        info.setSecondname(rs.getString("secondname"));
        info.setMiddlename(rs.getString("middlename"));
        info.setMobilephone(rs.getString("mobilephone"));
        info.setHomephone(rs.getString("homephone"));
        info.setAdress(rs.getString("adress"));
        info.setEmail(rs.getString("email"));
        info.setUsers_name(rs.getString("users_name"));

        return info;
    }
}
