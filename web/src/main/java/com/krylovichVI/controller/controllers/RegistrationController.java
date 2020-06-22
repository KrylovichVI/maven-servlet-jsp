package com.krylovichVI.controller.controllers;

import com.krylovichVI.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegistrationController{
    private AuthUserService authUserService;

    @Autowired
    public RegistrationController(AuthUserService authUserService){
        this.authUserService = authUserService;
    }

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String registration(HttpServletRequest req){
        String login = req.getParameter("login-reg");
        String password = req.getParameter("pass-reg");
        String role = req.getParameter("role-reg");

        if(!login.isEmpty()  && !password.isEmpty()){
            authUserService.saveAuthUser(login, password, role);
            return "redirect:/login";
        } else {
            return "redirect:/registration";
        }
    }
}
