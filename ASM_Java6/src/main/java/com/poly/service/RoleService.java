package com.poly.service;

import com.poly.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(String cust);
}
