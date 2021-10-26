package com.project.restaurantservice.services;

import com.project.restaurantservice.models.Courier;
import com.project.restaurantservice.models.Order;
import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.restaurantservice.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void test() {



    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public void addNewOrder(User user, List<Product> chosenProducts) {

    }

    public void addNewOrder(Long userId, Long courierId, String street, String city, String zip, String dateStr) {
        Order order = new Order(userId, courierId, street, city, zip, dateStr);
        orderRepository.save(order);
    }
}
