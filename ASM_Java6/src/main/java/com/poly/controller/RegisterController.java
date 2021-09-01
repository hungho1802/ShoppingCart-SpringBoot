package com.poly.controller;

import com.poly.entity.Account;
import com.poly.entity.Authorities;
import com.poly.entity.Role;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    RoleService roleService;

    @RequestMapping("security/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "security/login/form";
    }

    @RequestMapping(value = "security/register" , method = RequestMethod.POST)
    public String save(Model model, @Validated @ModelAttribute("account") Account account,
                       Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("message","Please fill in the information below!");
            return "security/login/form";
        }

        try {
            accountService.create(account);
            Role role = roleService.findById("CUST");
            Authorities auth = new Authorities(account,role);
            authorityService.create(auth);
            model.addAttribute("message","Your account was created successfully!");
        }catch (Exception e){
            model.addAttribute("message",e.getMessage());
        }

        model.addAttribute("account",account);
        return "security/login/form";
    }
}
