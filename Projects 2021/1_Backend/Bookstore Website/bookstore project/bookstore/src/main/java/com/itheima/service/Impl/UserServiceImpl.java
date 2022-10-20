package com.itheima.service.Impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.factory.MapperFactory;
import com.itheima.service.UserService;
import com.itheima.util.MD5Util;
import com.itheima.util.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {

    @Override
    public User verifyUserLogin(String username, String password) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);
            password = MD5Util.md5(password);
            return userDao.findByUsernameAndPwd(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean findUserByUsername(String username) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);
            User user = userDao.findByUsername(username);
            return user != null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
