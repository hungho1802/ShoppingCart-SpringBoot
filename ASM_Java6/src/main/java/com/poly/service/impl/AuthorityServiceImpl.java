package com.poly.service.impl;

import com.poly.entity.Account;
import com.poly.entity.Authorities;
import com.poly.repository.AccountRepository;
import com.poly.repository.AuthorityRepository;
import com.poly.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public List<Authorities> findAuthoritiesOfAdministrators() {
        List<Account> accounts = accountRepository.getAdministrators();
        return authorityRepository.authoritiesOf(accounts);
    }

    @Override
    public List<Authorities> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authorities create(Authorities auth) {
        return authorityRepository.save(auth);
    }

    @Override
    public void delete(Integer id) {
        authorityRepository.deleteById(id);
    }
}
