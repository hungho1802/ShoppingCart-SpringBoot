package com.poly.service.impl;

import com.poly.entity.OrderDetail;
import com.poly.repository.OrderDetailRepository;
import com.poly.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findAllByOder(Long id) {
        return orderDetailRepository.findAllByOrder(id);
    }
}
