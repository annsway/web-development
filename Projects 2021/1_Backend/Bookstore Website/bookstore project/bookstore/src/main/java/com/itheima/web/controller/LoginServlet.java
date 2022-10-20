package com.itheima.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.service.Impl.UserServiceImpl;
import com.itheima.service.UserService;
import com.itheima.web.BaseServlet;

@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String operation = req.getParameter("operation");
        if ("checkUsername".equals(operation)) {
            this.checkUsername(req, resp);
        } else if ("login".equals(operation)) {
            this.login(req, resp);
        }

    }

    private void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("username");

        boolean foundUser = userService.findUserByUsername(username);

        if (foundUser) {
            resp.getWriter().write("<font color='green'>Valid username &#10003;</font>");
        } else {
            resp.getWriter().write("<font color='red'>Invalid username</font>");
        }

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // user credential verification
        User user = userService.verifyUserLogin(username, password);
        if (user != null) {
            System.out.println(user);
            req.getSession().setAttribute("username", user.getUsername());
            req.getSession().setAttribute("loginStr", true);
            req.getRequestDispatcher(req.getContextPath() + "homeServlet?operation=list").forward(req, resp);
        } else {
            System.out.println("Invalid username or password.");
            req.getSession().setAttribute("loginStr", false);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
