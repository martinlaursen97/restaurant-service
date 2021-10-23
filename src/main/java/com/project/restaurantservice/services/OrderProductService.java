package com.project.restaurantservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.restaurantservice.models.OrderProduct;
import com.project.restaurantservice.repositories.OrderProductRepository;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public void addNewOrderProduct(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }
}
