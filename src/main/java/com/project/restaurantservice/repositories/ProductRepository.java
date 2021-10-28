package com.project.restaurantservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.restaurantservice.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    List<Product> searchFor(String keyword);

    @Query("SELECT p FROM Product p WHERE p.productName = ?1")
    Product findById2(Long id);

    @Query("SELECT p.productName FROM Product p WHERE p.productId = ?1")
    String getProductName(Long id);

}
