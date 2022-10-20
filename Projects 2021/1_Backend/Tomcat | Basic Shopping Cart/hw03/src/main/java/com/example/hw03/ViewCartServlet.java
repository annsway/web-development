package com.example.hw03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        HttpSession session = req.getSession();
        HashMap<String, Product> cartMap = (HashMap<String, Product>) session.getAttribute("cart");

        PrintWriter out = resp.getWriter();
        if (cartMap == null || cartMap.size() == 0) {
            out.println("Nothing in shopping cart.");
        } else {
            for (String key : cartMap.keySet()) {
                Product product = DB.getProduct(key);
                out.println("Product Name: " + product.getProdName() + "<br/>");
                out.println("Product Price: " + product.getPrice() + "<br/>");
                out.println("Qty in cart: " + product.getQty() + "<br/><br/>");
                out.println();
            }
        }

        out.println("<a href='home.html'>Return to the home page. </a>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
