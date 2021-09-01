package com.poly.api.controller;
import com.poly.entity.Account;
import com.poly.entity.Authorities;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/authorities")
public class AuthorityAPIController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public List<Authorities> findAll(@RequestParam("admin")Optional<Boolean> isAdmin){
        if(isAdmin.orElse(false)){
            return authorityService.findAuthoritiesOfAdministrators();
        }
        return authorityService.findAll();
    }

    @PostMapping
    public Authorities post(@RequestBody Authorities auth){
        return authorityService.create(auth);
    }

    @DeleteMapping("id")
    public void delete(@PathVariable("id") Integer id){
        authorityService.delete(id);
    }
}
