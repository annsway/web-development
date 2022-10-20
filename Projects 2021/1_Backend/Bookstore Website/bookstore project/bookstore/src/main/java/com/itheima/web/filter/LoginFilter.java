package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
public class LoginFilter implements Filter {
    private FilterConfig filterConfig;

    /**
     * 初始化方法，获取过滤器的配置对象
     *
     * @param filterConfig
     * @throws ServletException
     */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession session;
        try {
            request = (HttpServletRequest) servletRequest;
            response = (HttpServletResponse) servletResponse;
//            response.setContentType("text/html; charset=utf-8");

            session = request.getSession();

            String url = request.getRequestURI();

            if (url.endsWith(".css")
                    || url.endsWith(".js")
                    || url.endsWith(".jpg")
                    || url.endsWith(".gif")
                    || url.endsWith(".png")
                    || url.endsWith("login.jsp")
                    || url.endsWith("unauthorized.jsp")
                    || url.endsWith("loginServlet")
                    || url.endsWith("index.jsp")
            ) {
                filterChain.doFilter(request, response);
                return;
            }

            Boolean loginObj = (Boolean) session.getAttribute("loginStr");

            System.out.println(loginObj);

            //3.比对本次操作是否在当前登录人允许的操作范围内
            if (loginObj == null) {
                System.out.println(1);
                response.sendRedirect(request.getContextPath()+"/login.jsp");
                response.getWriter().write("<font color=red>Invalid username or password!</font>");

            }

            else if (loginObj) {
                System.out.println(2);

                //3.1如果允许，放行
                filterChain.doFilter(request, response);
                return;

            }else{
                System.out.println(3);
                // reset
                loginObj = null;
                response.getWriter().write("<font color=red>Invalid username or password!</font>");
                //3.2不允许跳转到非法访问页
                response.sendRedirect(request.getContextPath()+"/unauthorized.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
