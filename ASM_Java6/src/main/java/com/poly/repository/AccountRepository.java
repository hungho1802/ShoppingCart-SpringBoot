package com.poly.repository;

import com.poly.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,String> {
    @Query("SELECT DISTINCT  ar.account FROM Authorities ar WHERE ar.role.id IN ('DIRE','STAF')")
    List<Account> getAdministrators();
}
