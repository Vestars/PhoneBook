package com.trans.lardi.dao;

import com.trans.lardi.db.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytriy on 31.10.2016.
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFullname(rs.getString("fullname"));
        user.setAuthority(rs.getString("authority"));
        user.setEnabled(rs.getBoolean("enabled"));

        return user;
    }
}
