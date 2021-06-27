package com.nagarro.javaAdvance.service;

import com.nagarro.javaAdvance.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
