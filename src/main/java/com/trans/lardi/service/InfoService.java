package com.trans.lardi.service;

import com.trans.lardi.db.Info;

import java.util.List;

/**
 * Created by Dmytriy on 30.10.2016.
 */
public interface InfoService {
    List<Info> getAllInformation();

    List<Info> getAllInfo(String username);

    boolean saveOrUpdate(Info info);

    boolean delete(long id);

    Info getInfo(long id);

    List<Info> search(String pattern, String users_name);
}
