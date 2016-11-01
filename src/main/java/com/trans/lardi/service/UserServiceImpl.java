package com.trans.lardi.service;

import com.trans.lardi.dao.UserDAO;
import com.trans.lardi.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public boolean save(User user) {
        return userDAO.save(user);
    }

    public boolean exists(String username) {
        return userDAO.exists(username);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

}
