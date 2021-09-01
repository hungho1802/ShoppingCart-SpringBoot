package com.poly.repository;

import com.poly.entity.InventoryProduct;
import com.poly.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    List<Product> findByCategoryId(String cid);

    @Query("SELECT new InventoryProduct (o.category, sum(o.price), count(o)) " +
            "FROM Product o " +
            "GROUP BY o.category " +
            "ORDER BY sum(o.price) DESC")
    List<InventoryProduct> reportInventory();

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findByRangeSlider(Double min, Double max);

    @Query(value = "FROM Product ORDER BY id ASC")
    List<Product> findTop10Products();

    @Query(value = "FROM Product ORDER BY id ASC")
    List<Product> getNewestProducts();

    @Query(value = "FROM Product  WHERE sold > 300")
    List<Product> getPopularProducts();

    @Query(value = "FROM Product ORDER BY id DESC")
    List<Product> getLastestProduct();
}
