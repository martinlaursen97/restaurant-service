package com.project.restaurantservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.restaurantservice.models.Product;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1% AND p.active = 1")
    List<Product> searchFor(String keyword);

    @Query("SELECT p FROM Product p WHERE p.productId = ?1")
    Product findById2(Long id);

    @Query("SELECT p.productName FROM Product p WHERE p.productId = ?1  AND p.active = 1")
    String getProductName(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET active = 0 WHERE product_id = ?1", nativeQuery = true)
    void setProductInactive(Long productId);

    @Query("SELECT p FROM Product  p WHERE p.active = 1")
    List<Product> findAllActiveProducts();
}
