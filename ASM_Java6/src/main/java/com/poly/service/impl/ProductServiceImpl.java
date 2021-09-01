package com.poly.service.impl;

import com.poly.entity.InventoryProduct;
import com.poly.entity.Product;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findByCategoryId(String cid) {
        return productRepository.findByCategoryId(cid);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<InventoryProduct> reportInventory() {
        return productRepository.reportInventory();
    }

    @Override
    public Long count() {
        return productRepository.count();
    }

    @Override
    public List<Product> findByRangeSlider(Double min, Double max) {
        return productRepository.findByRangeSlider(min,max);
    }

    @Override
    public List<Product> getNotifications() {
        return productRepository.findTop10Products();
    }

    @Override
    public List<Product> getNewestProducts() {
        return productRepository.getNewestProducts();
    }

    @Override
    public List<Product> getPopularProducts() {
        return productRepository.getPopularProducts();
    }

    @Override
    public List<Product> getLastestProduct() {
        return productRepository.getLastestProduct();
    }


}
