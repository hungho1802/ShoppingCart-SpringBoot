package com.poly.repository;

import com.poly.entity.Account;
import com.poly.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
