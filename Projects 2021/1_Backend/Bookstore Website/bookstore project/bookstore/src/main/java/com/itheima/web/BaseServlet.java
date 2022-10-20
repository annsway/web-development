package com.itheima.web;

import com.itheima.service.Impl.ProductServiceImpl;
import com.itheima.service.Impl.UserServiceImpl;
import com.itheima.service.ProductService;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    protected ProductService productService;
    protected UserService userService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
        userService = new UserServiceImpl();
    }
}
