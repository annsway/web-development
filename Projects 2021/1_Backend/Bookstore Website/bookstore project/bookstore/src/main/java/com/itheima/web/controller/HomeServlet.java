package com.itheima.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/store")
// uri: /servlet/pageServlet
// uri: /servlet/findByIdServlet
@WebServlet("/homeServlet")
public class HomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("This is HomeServlet...");
        System.out.println(req.getParameter("operation"));

        ////
        String operation = req.getParameter("operation");

        if ("list".equals(operation)) {
            this.list(req, resp);
        } else if ("showByCategory".equals(operation)) {
            this.showByCategory(req, resp);
        } else if ("findProductName".equals(operation)) {
            this.findProductName(req, resp);
        } else if ("showSearchResult".equals(operation)) {
            this.showSearchResult(req, resp);
        } else if ("findBookById".equals(operation)) {
            this.findBookById(req, resp);
        } else if ("updateProduct".equals(operation)) {
            this.updateProduct(req, resp);
        } else if ("delete".equals(operation)) {
            this.delete(req, resp);
        } else if ("addProduct".equals(operation)) {
            this.add(req, resp);
        }

    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("this is list()");
        int curPage = 1;
        int size = 5; // show how many items per page

        if (StringUtils.isNotBlank(req.getParameter("curPage"))) {
            curPage = Integer.parseInt(req.getParameter("curPage"));
        }
        if (StringUtils.isNotBlank(req.getParameter("size"))) {
            size = Integer.parseInt(req.getParameter("size"));
        }

        PageInfo all = productService.showAllProducts(curPage, size);

        req.setAttribute("pb", all);

        req.getRequestDispatcher("/WEB-INF/pages/list/product_list.jsp").forward(req, resp);
    }

    private void showByCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this is showByCategory()");

        String category = req.getParameter("category");
        req.setAttribute("category", category);

        int curPage = 1;
        int size = 5;
        if (StringUtils.isNotBlank(req.getParameter("curPage"))) {
            curPage = Integer.parseInt(req.getParameter("curPage"));
        }
        if (StringUtils.isNotBlank(req.getParameter("size"))) {
            size = Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = productService.showByCategory(category, curPage, size);
        req.setAttribute("pb", all);
        req.getRequestDispatcher("/WEB-INF/pages/list/product_list.jsp").forward(req, resp);


    }


    private void findProductName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this is findProductName()");
        String productName = req.getParameter("name");
//        System.out.println(productName);

        int curPage = 1;
        int size = 5;
        if (StringUtils.isNotBlank(req.getParameter("curPage"))) {
            curPage = Integer.parseInt(req.getParameter("curPage"));
        }
        if (StringUtils.isNotBlank(req.getParameter("size"))) {
            size = Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = productService.findByProductName(productName, curPage, size);

        List list = all.getList();
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(list);
        resp.getWriter().write(value);

//        System.out.println(all);

    }

    private void showSearchResult(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this is showSearchResult()");
        String productName = req.getParameter("name");
//        System.out.println(productName);

        int curPage = 1;
        int size = 5;
        if (StringUtils.isNotBlank(req.getParameter("curPage"))) {
            curPage = Integer.parseInt(req.getParameter("curPage"));
        }
        if (StringUtils.isNotBlank(req.getParameter("size"))) {
            size = Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = productService.findByProductName(productName, curPage, size);

        req.setAttribute("pb", all);
        req.getRequestDispatcher("/WEB-INF/pages/list/product_list.jsp").forward(req, resp);

    }

    private void findBookById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this is findBookById()");
        String bookId = req.getParameter("id");

        Product product = productService.findByProductId(bookId);
        req.setAttribute("book", product);
        req.getRequestDispatcher("/WEB-INF/pages/list/edit.jsp").forward(req, resp);
    }


    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=utf-8");
        System.out.println("this is updateProduct()");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Double qty1 = Double.parseDouble(req.getParameter("qty"));
        Integer pnum = qty1.intValue();
        Double price = Double.parseDouble(req.getParameter("price"));
        String category = req.getParameter("category");

        // plan b: Beanutil
        Product product = new Product(id, name, pnum, price, category);

        int flag = productService.updateProduct(product);

//        PrintWriter pw = resp.getWriter();
//        if (flag == 1) {
//            System.out.println("update ok ---- ");
//            pw.println("<script type=\"text/javascript\">");
//            pw.println("alert('Successfully updated!');");
//            pw.println("location='/WEB-INF/pages/list/product_list.jsp';");
//        } else {
//            pw.println("<script type=\"text/javascript\">");
//            pw.println("alert('Failed to update!');");
//        }
//        pw.println("</script>");
        resp.sendRedirect(req.getContextPath() + "homeServlet?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("this is delete()");
        String id = req.getParameter("Id");
        int flag = productService.deleteProduct(id);
//        Product product = productService.findByProductId(id);
//        int flag = productService.deleteProduct(product);

        System.out.println("flag = " + flag);
        resp.sendRedirect(req.getContextPath() + "homeServlet?operation=list");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("this is add()");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Double qty1 = Double.parseDouble(req.getParameter("qty"));
        Integer pnum = qty1.intValue();
        Double price = Double.parseDouble(req.getParameter("price"));
        String category = req.getParameter("category");

        // plan b: Beanutil
        Product product = new Product(id, name, pnum, price, category);

        int flag = productService.addProduct(product);

        resp.sendRedirect(req.getContextPath() + "homeServlet?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
