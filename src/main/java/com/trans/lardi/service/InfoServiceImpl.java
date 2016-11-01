package com.trans.lardi.service;

import com.trans.lardi.dao.InfoDAO;
import com.trans.lardi.db.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("infoService")
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoDAO infoDAO;

    @Override
    public List<Info> getAllInformation() {
        return infoDAO.getAllInformation();
    }

    public List<Info> getAllInfo(String username) {
        return infoDAO.getAllInfo(username);
    }

    public boolean saveOrUpdate(Info info) {
        if (info.getId() == 0) {
            return infoDAO.save(info);
        }
        if (info.getId() != 0) {
            return infoDAO.update(info);
        }
        return false;
    }

    public boolean delete(long id) {
        return infoDAO.delete(id);
    }

    public Info getInfo(long id) {
        return infoDAO.getInfo(id);
    }

    public List<Info> search(String pattern, String users_name) {

        return infoDAO.search(pattern, users_name);
    }
}
