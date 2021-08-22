package com.itheima;


import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // 2. 加载spring配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 3. 获取资源 --- 这里就不用使用 new 关键字来获取 service 层中的资源了，而是通过 spring 的配置文件
        UserService userService = (UserService)ctx.getBean("userService");

        userService.save();
    }
}
