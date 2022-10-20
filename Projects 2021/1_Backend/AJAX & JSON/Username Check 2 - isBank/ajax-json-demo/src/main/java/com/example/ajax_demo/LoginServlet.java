package com.example.ajax_demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        HashMap<String, String> result = new HashMap<>(); // Q: Why use a Map? Ans: we will convert it to JSON in the end.
        if (username == null || username.trim().equals("")) {
            result.put("type", "0");
            result.put("msg", "Error: username is blank.");
        } else {
            result.put("type", "1");
            result.put("msg", "Welcome back!");
        }

        resp.setContentType("application/json;charset=utf-8"); // tell the client browser we're sending back json data
        // convert map to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
