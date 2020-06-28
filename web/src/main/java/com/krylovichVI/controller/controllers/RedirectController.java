package com.krylovichVI.controller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class RedirectController {
    @GetMapping("/403")
    public String forbidden(){
        return "403";
    }
}
