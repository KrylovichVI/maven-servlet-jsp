package com.krylovichVI.servlet.servlets;

import com.krylovichVI.service.BlackListService;
import com.krylovichVI.service.impl.DefaultBlackListService;
import com.krylovichVI.servlet.WebUtils;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.impl.DefaultAuthUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private AuthUserService authUserService;
    private BlackListService blackListService;

    @Override
    public void init() throws ServletException {
        authUserService = DefaultAuthUserService.getInstance();
        blackListService = DefaultBlackListService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser)req.getSession().getAttribute("authUser");
        if(authUser != null){
            WebUtils.forwardToJsp("page", req, resp);
        } else {
            WebUtils.forwardToJsp("login", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser user = authUserService.login(login, password);
        if(blackListService.existUserInBlackList(user) && user != null){
            req.setAttribute("error", "This user consist of black list");
            WebUtils.forwardToJsp("login", req, resp);
        }else {
            if (user != null) {
                req.getSession().setAttribute("authUser", user);
                WebUtils.sendRedirect( "/page", req, resp);
            } else {
                WebUtils.sendRedirect( "/login", req, resp);
            }
        }
    }

}
