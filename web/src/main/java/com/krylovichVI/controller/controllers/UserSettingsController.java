package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/settings")
public class UserSettingsController{
    private UserService userService;

    @Autowired
    public UserSettingsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserSettings(UsernamePasswordAuthenticationToken authUser, Model model){

        model.addAttribute("userInfo", userService.getUserByAuthUser((AuthUser) authUser.getPrincipal()));
        model.addAttribute("authUser", authUser);
        return "settings";
    }

    @PostMapping
    public String updateUserSettings(
            UsernamePasswordAuthenticationToken authUser,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phone,
            @RequestParam String email
                                     ){
        User user = getUserInfo((AuthUser) authUser.getPrincipal(), firstName, lastName, phone, email);
        userService.updateUserInfo(user);
        return "redirect:/settings";
    }

    private User getUserInfo(AuthUser authUser, String firstName, String lastName, String phone, String email) {
        User user = authUser.getUser();
        user.setId(authUser.getUser().getId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        return user;
    }
}
