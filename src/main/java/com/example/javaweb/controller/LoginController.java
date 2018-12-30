package com.example.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String  returnString ()
    {
        return  "login";
    }
    @RequestMapping("/login-error")
    public String loginError(){
        return "login-error";
    }
}
