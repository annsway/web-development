package com.example.hw03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ViewProductServlet")
public class ViewProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String pid = req.getParameter("pid");
        Product product = DB.getProduct(pid);
        PrintWriter out = resp.getWriter();
        out.println("<form action='/sale/AddToCartServlet' method='get'>");
        out.println("Product ID:<input type='text' name='pid' value='" + product.getPID() + "'/><br/>");
        out.println("Product Name:" + product.getProdName() + "<br/>");
        out.println("Product Desc:" + product.getProdDesc() + "<br/>");
        out.println("Product Price:" + product.getPrice() + "<br/>");
        out.println("Qty to buy:<input type='text' name='num' value='1'/><br/>");
        out.println("<input type='submit' value='Add to My Shopping Cart'/>");
        out.println("</form>");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
