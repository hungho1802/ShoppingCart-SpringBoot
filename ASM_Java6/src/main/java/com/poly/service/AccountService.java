package com.poly.service;

import com.poly.entity.Account;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.List;

public interface AccountService {

    Account findById(String username);

    List<Account> findAll();

    List<Account> getAdministrators();

    Long count();

    Account create(Account account);

    Account update(Account account);

    void delete(String id);

    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2);
}
