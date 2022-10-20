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
import java.util.Map;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8"); // must add this line in order for HTML syntax to work

        /**Content types are included in HTTP responses because the same, byte for byte sequence of values in
         * the content could be interpreted in more than one way.(*)
         Remember that http can transport more than just HTML (js, css and images are obvious examples),
         and in some cases, the receiver will not know what type of object it's going to receive.
         */
        PrintWriter out = resp.getWriter();

        String pid = req.getParameter("pid");
        int num = Integer.parseInt(req.getParameter("num")); //qty
        HttpSession session = req.getSession();

        Map<String, Product> cartMap = (Map<String, Product>) session.getAttribute("cart");

        if (cartMap == null) {
            cartMap = new HashMap<>();
            session.setAttribute("cart", cartMap);
        }

        if (!cartMap.containsKey(pid)) {
            Product product = DB.getProduct(pid);
            product.setQty(1);
            cartMap.put(pid, product);
        } else {
            Product product = cartMap.get(pid);
            product.setQty(product.getQty() + num);
        }

        out.write("This product has been added to your shopping cart. <br/>");
        out.println("<a href='home.html'>Return to Home Page</a><br/>");
        out.println("<a href='ViewCartServlet'>View My Shopping Cart</a><br/>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
