package com.krylovichVI.controller.controllers;

import com.krylovichVI.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registration(
            @RequestParam String login,
            @RequestParam(name = "pass") String password,
            @RequestParam String role
    ){
        if(!login.isEmpty()  && !password.isEmpty()){
            authUserService.saveAuthUser(login, password, role);
            return "redirect:/login";
        } else {
            return "redirect:/registration";
        }
    }
}
