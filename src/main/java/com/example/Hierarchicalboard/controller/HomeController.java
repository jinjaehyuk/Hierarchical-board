package com.example.Hierarchicalboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@CookieValue(value="savedEmail",defaultValue="")String savedEmail,  @RequestParam(required = false) boolean error, Model model){
        model.addAttribute("error",error);
        model.addAttribute("savedEmail",savedEmail);
        boolean rememberCheck=false;
        if(!savedEmail.equals("")){
            rememberCheck=true;
        }
        model.addAttribute("rememberCheck",rememberCheck);
        System.out.println(rememberCheck);
        return "login";
    }
}
