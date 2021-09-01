package com.poly.service;

import com.poly.entity.InventoryProduct;
import com.poly.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCategoryId(String cid);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);

    List<InventoryProduct> reportInventory();

    Long count();

    List<Product> findByRangeSlider(Double min, Double max);

    List<Product> getNotifications();

    List<Product> getNewestProducts();

    List<Product> getPopularProducts();

    List<Product> getLastestProduct();
}
