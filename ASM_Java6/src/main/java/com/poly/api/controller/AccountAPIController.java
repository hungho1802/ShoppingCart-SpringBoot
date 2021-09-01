package com.poly.api.controller;
import com.poly.entity.Account;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/accounts")
public class AccountAPIController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> isAdmin){
        if(isAdmin.orElse(false)){
            return accountService.getAdministrators();
        }
        return accountService.findAll();
    }

    @GetMapping("{id}")
    public Account getOne(@PathVariable("id") String username){
        return accountService.findById(username);
    }

    @PostMapping
    public Account create(@RequestBody Account account){
        return accountService.create(account);
    }

    @PutMapping("{id}")
    public Account update(@PathVariable("id") String id,@RequestBody Account account){
        return accountService.update(account);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id){
        accountService.delete(id);
    }

    @GetMapping("count")
    public Long count(){
        return accountService.count();
    }

}
