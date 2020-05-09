package com.krylovichVI.servlet.filter;

import com.krylovichVI.pojo.AuthUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/page", "/blackList", "/logout", "/adminOrders", "/userOrders", "/settings", "/userList",
        "/deleteOrderServlet"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if(authUser != null){
            chain.doFilter(req, resp);
        }else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
