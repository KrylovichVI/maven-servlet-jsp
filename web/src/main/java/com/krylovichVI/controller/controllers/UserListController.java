package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
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
@RequestMapping("/userList")
public class UserListController{
    private AuthUserService authUserService;
    private BlackListService blackListService;

    @Autowired
    public UserListController(AuthUserService authUserService, BlackListService blackListService) {
        this.authUserService = authUserService;
        this.blackListService = blackListService;
    }


    @GetMapping
    public String getUsersList(Model model){
        List<AuthUser> usersRole = authUserService.getUsers();

        model.addAttribute("containsInBlackList", "This user contain in black list");
        model.addAttribute("usersRole", usersRole);

        return "userList";
    }

    @PostMapping("{usrName}")
    public String addUserInBlackList(@PathVariable String usrName){
        AuthUser user = authUserService.getByLogin(usrName);
        blackListService.addUserInBlackList(user);

        return "redirect:/userList";
    }


}
