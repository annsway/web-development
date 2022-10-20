package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User findByUsernameAndPwd(@Param("username") String username, @Param("password") String password);
    User findByUsername(@Param("username") String username);
}
