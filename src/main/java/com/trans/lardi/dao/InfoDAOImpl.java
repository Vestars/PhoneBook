package com.trans.lardi.dao;

import com.trans.lardi.db.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("infoDAO")
public class InfoDAOImpl implements InfoDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Info> getAllInformation() {
        String SQL = "select * from info, users where info.users_name=users.username";
        return jdbcTemplate.query(SQL, new InfoMapper());
    }

    @Override
    public List<Info> getAllInfo(String username) {
        String SQL = "select * from info, users where info.users_name=users.username and users.enabled=true and info.users_name=:username";
        return jdbcTemplate.query(SQL, new MapSqlParameterSource("username", username), new InfoMapper());
    }

    @Override
    public Info getInfo(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String SQL = "select * from info, users where info.users_name=users.username and users.enabled=true and id=:id";
        return jdbcTemplate.queryForObject(SQL, params, new InfoMapper());
    }

    @Override
    public List<Info> search(String pattern, String users_name) {

        String SQL = "SELECT * FROM info WHERE info.users_name=:users_name " +
                "and concat(secondname, firstname, middlename, mobilephone, homephone, " +
                "adress, email) like :pattern";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("users_name", users_name);
        params.addValue("pattern", "%" + pattern + "%");
        return jdbcTemplate.query(SQL, params, new InfoMapper());
    }

    @Transactional
    @Override
    public boolean save(Info info) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(info);
        String SQL = "insert into info (secondname, firstname, middlename, mobilephone, homephone, adress, email, users_name) " +
                "values (:secondname, :firstname, :middlename, :mobilephone, :homephone, :adress, :email, :users_name)";

        return jdbcTemplate.update(SQL, params) == 1;
    }

    @Transactional
    @Override
    public boolean update(Info info) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(info);
        String SQL = "update info set secondname=:secondname, firstname=:firstname, middlename=:middlename, mobilephone=:mobilephone," +
                " homephone:=homephone, adress=:adress, email=:email, users_name=users_name where id=:id";
        return jdbcTemplate.update(SQL, params) == 1;
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        int countOfDeletedRows = jdbcTemplate.update("delete from info where id=:id", new MapSqlParameterSource("id", id));
        System.out.println("Count " + countOfDeletedRows);
        return countOfDeletedRows == 1;
    }
}
