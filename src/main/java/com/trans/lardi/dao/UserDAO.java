package com.trans.lardi.dao;

import com.trans.lardi.db.User;

import java.util.List;

public interface UserDAO {

    boolean save(User user);

    boolean exists(String username);

    List<User> getAllUsers();

    User findByUsername(String username);
}
