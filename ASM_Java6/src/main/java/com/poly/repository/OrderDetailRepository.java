package com.poly.repository;

import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query("SELECT o FROM OrderDetail o " +
            "WHERE o.order.id = ?1 ")
    public List<OrderDetail> findAllByOrder(Long id);
}
