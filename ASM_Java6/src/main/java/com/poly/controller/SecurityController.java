package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("/security/login/form")
    public String loginForm(Model model){
        model.addAttribute("message","Please login!!");
        return "security/login/form";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model){
        model.addAttribute("message","Login Successfully !!");
        return "security/login/form";
    }

    @RequestMapping("/security/login/error")
    public String loginErorr(Model model){
        model.addAttribute("message","The username or password is incorrect !!");
        return "security/login/form";
    }

    @RequestMapping("/security/unauthorized")
    public String unauthorized(Model model){
        model.addAttribute("message","You are not authorize to access this page!!");
        return "security/login/form";
    }

    @RequestMapping("/security/logoff/success")
    public String logoutSuccess(Model model){
        model.addAttribute("message","You just logged out!!");
        return "security/login/form";
    }

    @RequestMapping("/security/register/form")
    public String register(Model model){

        return "security/login/form";
    }
}
