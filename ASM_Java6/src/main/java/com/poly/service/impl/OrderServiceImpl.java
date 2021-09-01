package com.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.repository.OrderDetailRepository;
import com.poly.repository.OrderRepository;
import com.poly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();

        //Save order
        Order order = mapper.convertValue(orderData,Order.class);
        orderRepository.save(order);
        System.out.println("Create an order");

        //Save order details
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>(){};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailRepository.saveAll(details);
        System.out.println("Create items of order");

        return order;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {

        return orderRepository.findByUsername(username);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
