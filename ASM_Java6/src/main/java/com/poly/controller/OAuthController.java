package com.poly.controller;

import com.poly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OAuthController {

    /*
     * OAuth2
     * */

    @Autowired
    private AccountService accountService;

    @RequestMapping("/oauth2/login/success")
    public String success(OAuth2AuthenticationToken oauth2){
        accountService.loginFromOAuth2(oauth2);
        return "forward:/security/login/success";
    }


}
