package com.project.restaurantservice.repositories;

import com.project.restaurantservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE restaurant.orders SET courier_id = (SELECT courier_id from couriers WHERE area_zip = ?1 ORDER BY RAND() LIMIT 1) WHERE(order_number = ?2)", nativeQuery = true)
    void assignCourierByZipAndOrderNumber(String zip, Long orderNumber);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO restaurant.order_products (product_id, order_number) VALUES(?1, ?2)", nativeQuery = true)
    void assignOrderProducts(Long productId, Long orderNumber);

    @Query(value = "SELECT o FROM Order o WHERE o.customerId = ?1")
    List<Order> getOrdersById(Long userId);

    @Query("SELECT o FROM Order o WHERE o.orderNumber = ?1")
    Order searchFor(Long keyword);

    @Query("SELECT o FROM Order o WHERE o.orderNumber = ?1")
    Order findByOrderNumber(Long keyword);

    @Query(value = "SELECT product_id FROM restaurant.order_products WHERE order_number = ?1", nativeQuery = true)
    List<Long> getOrderProducts(Long orderNumber);
}
