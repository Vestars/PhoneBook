package com.trans.lardi.dao;

import com.trans.lardi.db.Info;

import java.util.List;

/**
 * Created by Dmytriy on 26.10.2016.
 */
public interface InfoDAO {
    void init();
    List<Info> getAllInfoEntry();
    List<Info> getInfoEntries(String pattern);
    boolean save(Info info);
    boolean update(Info info);
    boolean delete(long id);

}
