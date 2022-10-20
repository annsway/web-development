package com.itheima.service;

import com.itheima.domain.User;

public interface UserService {
    User verifyUserLogin(String username, String password);

    boolean findUserByUsername(String username);
}
