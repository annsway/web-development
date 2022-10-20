package com.example.web;

import com.example.bean.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        UserService us = new UserServiceImpl();
        String username = req.getParameter("username");
        if (username == null || "".equals(username)) {
            resp.getWriter().write("");
        } else {
            //调用Service层根据用户名获得用户列表数据
            List<User> user = us.findByName(username);
            //将列表数据转换为json字符串
            ObjectMapper mapper = new ObjectMapper();
            //将json字符串发送到浏览器
            mapper.writeValue(resp.getWriter(), user);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
