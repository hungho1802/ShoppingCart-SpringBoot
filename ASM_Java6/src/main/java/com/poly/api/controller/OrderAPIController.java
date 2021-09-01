package com.poly.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.entity.Product;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderAPIController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping()
    public Order create(@RequestBody JsonNode orderData){
        return orderService.create(orderData);
    }

    @GetMapping("count")
    public Long count(){
        return orderService.count();
    }

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("find/{id}")
    public List<OrderDetail> findAllByOrder(@PathVariable("id") Long id){
        return orderDetailService.findAllByOder(id);
    }

}
