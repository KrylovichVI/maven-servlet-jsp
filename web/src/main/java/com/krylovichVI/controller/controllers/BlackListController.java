package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/blackList")
public class BlackListController {
    private AuthUserService authUserService;
    private BlackListService blackListService;

    @Autowired
    public BlackListController(AuthUserService authUserService, BlackListService blackListService) {
        this.authUserService = authUserService;
        this.blackListService = blackListService;
    }

    @GetMapping
    public String returnUsersOfBlackList(HttpServletRequest req){
        String errorBlackList = (String) req.getSession().getAttribute("errorBlackList");
        req.getSession().removeAttribute("errorBlackList");

        List<BlackList> usersOfBlackList = blackListService.getUsersOfBlackList();
        req.setAttribute("usersOfBlackList", usersOfBlackList);
        req.setAttribute("errorBlackList", errorBlackList);

        return "blackList";
    }

    @PostMapping
    public String deleteUserOfBlackList(HttpServletRequest req){
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

        return "redirect:/blackList";
    }


}
