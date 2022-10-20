package com.example.service;

import com.example.bean.User;
import com.example.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> findByName(String username) throws IOException {
        //1.加载核心配置文件
        InputStream is = Resources.getResourceAsStream("MyBatisConfig.xml");

        //2.获取SqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //3.通过工厂对象获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //4.获取StudentMapper接口的实现类对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //5.调用实现类对象中的方法，接收结果
        // 调用映射接口的实现类对象的selectByName()方法。底层解析该注解，拿到SQL 语句并执行和返回结果、释放资源
        List<User> userList = mapper.selectByName(username);

        //6.处理结果

        //7.释放资源
        sqlSession.close();
        is.close();

        return userList;
    }
}
