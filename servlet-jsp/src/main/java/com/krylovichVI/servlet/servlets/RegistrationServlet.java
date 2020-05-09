package com.krylovichVI.servlet.servlets;

import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import com.krylovichVI.servlet.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private AuthUserService authUserService;

    @Override
    public void init() throws ServletException {
        authUserService = DefaultAuthUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forwardToJsp("registration", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login-reg");
        String password = req.getParameter("pass-reg");
        String role = req.getParameter("role-reg");

        if(!login.isEmpty()  && !password.isEmpty()){
            authUserService.saveAuthUser(login, password, role);
            WebUtils.sendRedirect( "/login", req, resp);
        } else {
            WebUtils.sendRedirect( "/registration", req, resp);
        }
    }
}
