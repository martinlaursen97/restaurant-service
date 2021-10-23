package com.project.restaurantservice.services;

import com.project.restaurantservice.models.Courier;
import com.project.restaurantservice.models.Order;
import com.project.restaurantservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.restaurantservice.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void test() {
        User user = new User("martin", "laursen", "martin_laursen9@hotmail.com", "vejledalen 12, 2.tv", "ishøj", "2635", "42217458");
        Courier courier = new Courier("kenned", "larsen", "2635");




        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order order = new Order(
                user.getUserId(),
                courier.getCourierId(),
                user.getStreet(),
                user.getCity(),
                user.getZip(),
                sdf.format(dt));

        orderRepository.save(order);
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }
}
