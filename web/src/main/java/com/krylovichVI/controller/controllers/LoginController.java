package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BlackListService;
import com.krylovichVI.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path =  "/login")
public class LoginController {
    private AuthUserService authUserService;
    private BlackListService blackListService;
    private SecurityService securityService;

    @Autowired
    public LoginController(AuthUserService authUserService, BlackListService blackListService, SecurityService securityService) {
        this.authUserService = authUserService;
        this.blackListService = blackListService;
        this.securityService = securityService;
    }


    @GetMapping
    public String loginGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || "anonymousUser".equals(authentication.getPrincipal())){
            return "login";
        }
        return "redirect:/book";
    }

    @PostMapping
    public String login(@RequestParam String login, @RequestParam String password, Model model){
        AuthUser user = authUserService.login(login, password);
        if(blackListService.existUserInBlackList(user) && user != null){
            model.addAttribute("error", "This user consist of black list");
            return "redirect:/login";
        }else {
            if (user == null) {
                model.addAttribute("error", "login or password invalid");
                return "login";
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, getAuthorities(user));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/book";

        }
    }

    private List<GrantedAuthority> getAuthorities(AuthUser authUser) {
        return Arrays.asList((GrantedAuthority) () -> "ROLE_" + authUser.getRole().name());
    }

}
