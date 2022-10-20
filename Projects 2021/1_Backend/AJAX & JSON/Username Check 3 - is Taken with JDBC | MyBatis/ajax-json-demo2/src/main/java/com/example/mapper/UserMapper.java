package com.example.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import com.example.bean.User;

public interface UserMapper {
    //查询全部
    /**
     * Q: 为什么用List<User>作为返回值？
     * A: 如果User没有找到，那么返回给client是空data，也无法调用success function.
     *    这里使用List，如果没有在db找到指定username，那么将给client返回空List，仍然可以调用success function
     * */
    @Select("SELECT * FROM t_user WHERE LOWER(t_user.name) = #{username}")
    public abstract List<User> selectByName(String username);

}
