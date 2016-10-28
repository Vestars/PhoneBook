package com.trans.lardi.dao;

import com.trans.lardi.db.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InfoDAOImpl implements InfoDAO {
    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void init() {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Info> getAllInfoEntry() {
        return jdbcTemplate.query("SELECT * FROM info WHERE info.user_id = user.id", BeanPropertyRowMapper.newInstance(Info.class));
    }

    @Override
    public List<Info> getInfoEntries(String pattern) {
        return jdbcTemplate.query("SELECT * FROM info WHERE (firstname OR secondname OR mobilephone) =:%pattern%", BeanPropertyRowMapper.newInstance(Info.class));
    }

    @Transactional
    @Override
    public boolean save(Info info) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(info);
        String SQL = "INSERT INTO info (firstname, secondname, middlename, mobilephone, homephone, address, email) " +
                "values (:firstname, :secondname, :middlename, :mobilephone, :homephone, :address, :email)";

        return jdbcTemplate.update(SQL, params) == 1;
    }

    @Transactional
    @Override
    public boolean update(Info info) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(info);
        String SQL = "UPDATE info SET secondname=:secondname, firstname=:firstname, middlename=:middlename, mobilephone=:mobilephone," +
                " homephone:=homephone, address=:address, email=:email WHERE id=:id";

        return jdbcTemplate.update(SQL, params) == 1;
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(id);

        return jdbcTemplate.update("DELETE FROM info WHERE id=:id",params) == 1;
    }
}
