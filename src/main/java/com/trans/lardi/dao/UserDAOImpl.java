package com.trans.lardi.dao;

import com.trans.lardi.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
    private JdbcTemplate jt;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        String users = "CREATE TABLE IF NOT EXISTS `phonebook`.`users` (\n" +
                "  `username`  VARCHAR(45)  NOT NULL,\n" +
                "  `password`  VARCHAR(45)  NOT NULL,\n" +
                "  `fullname`  VARCHAR(255) NOT NULL,\n" +
                "  `authority` VARCHAR(45)  NOT NULL,\n" +
                "  `enabled`   TINYINT(1)   NOT NULL,\n" +
                "  UNIQUE INDEX `username_UNIQUE` (`username` ASC),\n" +
                "  PRIMARY KEY (`username`)\n" +
                ")";
        this.jt = new JdbcTemplate(dataSource);
        jt.execute(users);
        String info = "CREATE TABLE IF NOT EXISTS `phonebook`.`info` (\n" +
                "  `id`          INT          NOT NULL AUTO_INCREMENT,\n" +
                "  `firstname`   VARCHAR(255) NOT NULL,\n" +
                "  `secondname`  VARCHAR(255) NOT NULL,\n" +
                "  `middlename`  VARCHAR(255) NOT NULL,\n" +
                "  `mobilephone` VARCHAR(45)  NOT NULL,\n" +
                "  `homephone`   VARCHAR(45)  NULL,\n" +
                "  `adress`      VARCHAR(255) NULL,\n" +
                "  `email`       VARCHAR(255) NULL,\n" +
                "  `users_name`  VARCHAR(45)  NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
                "  INDEX `fk_info_users_idx` (`users_name` ASC),\n" +
                "  CONSTRAINT `fk_info_users`\n" +
                "  FOREIGN KEY (`users_name`)\n" +
                "  REFERENCES `phonebook`.`users` (`username`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                ")";
        this.jt = new JdbcTemplate(dataSource);
        jt.execute(info);
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public boolean save(User user) {
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(user);
        return jdbcTemplate.update("INSERT INTO users (username, fullname, password, authority, enabled) values (:username, :fullname, :password, :authority, :enabled)", beanPropertySqlParameterSource) == 1;
    }

    @Override
    public boolean exists(String username) {
        return jdbcTemplate.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username", username), Integer.class) > 0;
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User findByUsername(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        String SQL = "select * from users where username=:username";
        return jdbcTemplate.queryForObject(SQL, params, new UserMapper());
    }
}
