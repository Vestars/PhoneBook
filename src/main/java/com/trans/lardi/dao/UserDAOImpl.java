package com.trans.lardi.dao;

import com.trans.lardi.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void init() {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public boolean save(User user) {
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(user);
        return jdbcTemplate.update("INSRET INTO users (username, fullname, password, role) values (:username, :fullname, :password, :role)", beanPropertySqlParameterSource) == 1;
    }

    @Override
    public boolean exists(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE username=:username",
                new MapSqlParameterSource("username", username), Integer.class) > 0;
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM user", BeanPropertyRowMapper.newInstance(User.class));
    }
}
