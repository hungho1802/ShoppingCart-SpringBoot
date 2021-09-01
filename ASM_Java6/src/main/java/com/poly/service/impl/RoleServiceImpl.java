package com.poly.service.impl;

import com.poly.entity.Role;
import com.poly.repository.RoleRepository;
import com.poly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(String cust) {
        return roleRepository.findById(cust).get();
    }
}
