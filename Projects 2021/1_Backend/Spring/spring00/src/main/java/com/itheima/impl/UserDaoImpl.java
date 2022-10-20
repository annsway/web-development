package com.itheima.impl;

import com.itheima.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("userDao running...");
    }
}
