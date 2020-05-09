package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BlackListService;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import com.krylovichVI.service.impl.DefaultBlackListService;
import com.krylovichVI.servlet.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="blackListServlet" , urlPatterns = "/blackList")
public class BlackListServlet extends HttpServlet {
    private AuthUserService authUserService;
    private BlackListService blackListService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        blackListService = DefaultBlackListService.getInstance();
        authUserService = DefaultAuthUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorBlackList = (String) req.getSession().getAttribute("errorBlackList");
        req.getSession().removeAttribute("errorBlackList");

        List<BlackList> usersOfBlackList = blackListService.getUsersOfBlackList();
        req.setAttribute("usersOfBlackList", usersOfBlackList);
        req.setAttribute("errorBlackList", errorBlackList);

        WebUtils.forwardToJsp("blackList",req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        AuthUser byLogin = authUserService.getByLogin(userName);
        if(byLogin != null){
            if(blackListService.existUserInBlackList(byLogin)){
                blackListService.deleteUserOfBlackList(byLogin);
            }else {
                req.getSession().setAttribute("errorBlackList", "This user is not consist of black list");
            }
        }else {
            req.getSession().setAttribute("errorBlackList", "Field username is empty");
        }

        WebUtils.sendRedirect( "/blackList", req, resp);
    }


}
