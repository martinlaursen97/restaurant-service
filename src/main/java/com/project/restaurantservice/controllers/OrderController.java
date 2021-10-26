package com.project.restaurantservice.controllers;


import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.restaurantservice.services.OrderService;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/addOrder")
    public void addNewOrder() {

    }

    @RequestMapping("/finish")
    public String finish(WebRequest request) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        Long userId = user.getUserId();
        Long courierId = 404L;
        String street = user.getStreet();
        String city = user.getCity();
        String zip = user.getZip();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = new Date();

        String dateStr = formatter.format(dateNow);

        orderService.addNewOrder(userId, courierId, street, city, zip, dateStr);


        return "finish";
    }
}
