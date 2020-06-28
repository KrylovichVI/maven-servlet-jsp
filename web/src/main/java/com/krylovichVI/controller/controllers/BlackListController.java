package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String returnUsersOfBlackList(Model model){
        List<BlackList> usersOfBlackList = blackListService.getUsersOfBlackList();

        model.addAttribute("usersOfBlackList", usersOfBlackList);

        return "blackList";
    }

    @PostMapping("{authUserId}")
    public String deleteUserOfBlackList(@PathVariable("authUserId") Long authUserId){
        AuthUser authUser = authUserService.getById(authUserId);
        blackListService.deleteUserOfBlackList(authUser);

        return "redirect:/blackList";
    }


}
