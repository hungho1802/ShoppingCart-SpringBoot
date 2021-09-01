package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request, RedirectAttributes redirect) {

        request.getSession().setAttribute("productList", null);

        return "redirect:/home/index/1";
    }

    @RequestMapping({"/admin/dashboard","/admin/home/index"})
    public String admin(){
        return "redirect:/assets/admin/index.html";
    }

}