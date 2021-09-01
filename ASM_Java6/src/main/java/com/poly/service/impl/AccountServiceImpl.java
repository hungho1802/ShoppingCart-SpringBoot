package com.poly.service.impl;

import com.poly.entity.Account;
import com.poly.entity.Authorities;
import com.poly.entity.Role;
import com.poly.repository.AccountRepository;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder pe;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleService roleService;

    @Override
    public Account findById(String username) {
        return accountRepository.findById(username).get();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAdministrators() {
        return accountRepository.getAdministrators();
    }

    @Override
    public Long count() {
        return accountRepository.count();
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
        String fullname = oauth2.getPrincipal().getAttribute("name");
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());
        Role role = roleService.findById("CUST");

        //Create account
        Account account = create(new Account(email,password,fullname,email));
        System.out.println(account.toString());

        //Provide authority
        Authorities authorities = new Authorities(account,role);
        authorityService.create(authorities);

        //Build
        UserDetails user = User.withUsername(email)
                .password(pe.encode(password)).roles(role.getName()).build();

        Authentication auth = new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


}
