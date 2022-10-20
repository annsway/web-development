package com.itheima.impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;

public class UserServiceImpl implements UserService {
    private int num;
    private String version;
    private UserDao userDao;

    // 必须有 set methods，spring 才能为应用程序注入这些资源
    public void setNum(int num) {
        this.num = num;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        System.out.println("user service running..." + this.num + " version:" + this.version);
        userDao.save();
    }
}
