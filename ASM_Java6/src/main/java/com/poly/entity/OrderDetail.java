package com.poly.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "Orderdetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    Integer quantity;

    @ManyToOne
    @JoinColumn(name = "Productid")
    Product product;

    @ManyToOne
    @JoinColumn(name = "Orderid")
    Order order;

}
