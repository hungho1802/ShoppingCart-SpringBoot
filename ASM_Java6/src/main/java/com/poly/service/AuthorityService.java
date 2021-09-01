package com.poly.service;

import com.poly.entity.Authorities;

import java.util.List;

public interface AuthorityService {
    List<Authorities> findAuthoritiesOfAdministrators();

    List<Authorities> findAll();

    Authorities create(Authorities auth);

    void delete(Integer id);

}
