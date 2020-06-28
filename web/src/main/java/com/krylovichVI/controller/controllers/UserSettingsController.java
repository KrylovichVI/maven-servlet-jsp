package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String getUserSettings(@AuthenticationPrincipal AuthUser authUser, Model model){

        model.addAttribute("userInfo", userService.getUserByAuthUser(authUser));
        model.addAttribute("authUser", authUser);
        return "settings";
    }

    @PostMapping
    public String updateUserSettings(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phone,
            @RequestParam String email
                                     ){
        User user = getUserInfo(authUser, firstName, lastName, phone, email);
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
