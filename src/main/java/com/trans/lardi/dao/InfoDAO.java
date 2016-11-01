package com.trans.lardi.dao;

import com.trans.lardi.db.Info;

import java.util.List;

/**
 * Created by Dmytriy on 26.10.2016.
 */
public interface InfoDAO {
    List<Info> getAllInformation();

    List<Info> getAllInfo(String username);

    List<Info> search(String pattern, String users_name);

    Info getInfo(long id);

    boolean save(Info info);

    boolean update(Info info);

    boolean delete(long id);

}
