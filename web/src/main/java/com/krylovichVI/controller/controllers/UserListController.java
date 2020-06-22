package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("usersRole", usersRole);

            return "userList";
    }

    @PostMapping
    public String addUserInBlackList(@RequestParam String usrName, Model model){
        AuthUser user = authUserService.getByLogin(usrName);
        if(user != null){
            if(blackListService.existUserInBlackList(user)){
                model.addAttribute("errorList", "This user contain in black list");
            }else {
                blackListService.addUserInBlackList(user);
            }
        }else {
            model.addAttribute("errorUserList", "This user does not exist.");
        }
        return "redirect:/userList";
    }
}
