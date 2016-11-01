package com.trans.lardi.service;

import com.trans.lardi.db.User;

import java.util.List;

/**
 * Created by Dmytriy on 30.10.2016.
 */
public interface UserService {
    User findByUsername(String username);

    boolean save(User user);

    boolean exists(String username);

    List<User> getAllUsers();
}
