package com.poly.api.controller;

import com.poly.entity.Category;
import com.poly.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryAPIController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> getAll(){
        return categoryService.findAll();
    }

//    @GetMapping("{id}")
//    public Category getOne(@PathVariable("id") Integer id){
//        return categoryService.findById(id);
//    }


}
