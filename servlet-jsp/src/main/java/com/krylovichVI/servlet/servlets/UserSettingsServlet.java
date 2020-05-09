package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import com.krylovichVI.pojo.UserInfo;
import com.krylovichVI.service.UserService;
import com.krylovichVI.service.impl.DefaultUserService;
import com.krylovichVI.servlet.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userSettingsServlet",urlPatterns = "/settings")
public class UserSettingsServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = DefaultUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        req.setAttribute("userInf", userService.getUserByAuthUser(authUser));
        WebUtils.forwardToJsp("settings", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req);
        userService.updateUserInfo(user);
        WebUtils.sendRedirect( "/settings", req, resp);
    }

    private User getUser(HttpServletRequest req){
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");

        User user = authUser.getUser();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setUserInfo(new UserInfo(req.getParameter("phone"), req.getParameter("email")));
        return user;
    }
}
