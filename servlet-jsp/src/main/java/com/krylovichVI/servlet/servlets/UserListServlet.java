package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.BlackListService;
import com.krylovichVI.service.impl.DefaultBlackListService;
import com.krylovichVI.servlet.WebUtils;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.impl.DefaultAuthUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="userListServlet", urlPatterns = "/userList")
public class UserListServlet extends HttpServlet {
    private AuthUserService authUserService;
    private BlackListService blackListService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authUserService = DefaultAuthUserService.getInstance();
        blackListService = DefaultBlackListService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<AuthUser> usersRole = authUserService.getUsers();
            req.setAttribute("usersRole", usersRole);

            WebUtils.forwardToJsp("userList", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usrName = req.getParameter("usrName");
        AuthUser user = authUserService.getByLogin(usrName);
        if(user != null){
            if(blackListService.existUserInBlackList(user)){
                req.setAttribute("errorList", "This user contain in black list");
            }else {
                blackListService.addUserInBlackList(user);
            }
        }else {
            req.setAttribute("errorUserList", "This user does not exist.");
        }
        WebUtils.sendRedirect( "/userList", req, resp);
    }
}
