package com.project.restaurantservice.controllers;


import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.restaurantservice.services.OrderService;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
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
    public String finish(WebRequest request, Model model) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        List<Product> chosenProducts = (List<Product>) request.getAttribute("chosen", WebRequest.SCOPE_SESSION);

        Long userId = user.getUserId();
        String street = user.getStreet();
        String city = user.getCity();
        String zip = user.getZip();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = new Date();

        String dateStr = formatter.format(dateNow);

        Long orderNumber = orderService.addNewOrder(userId, street, city, zip, dateStr);
        orderService.assignCourier(user.getZip(), orderNumber);
        orderService.assignOrderProducts(chosenProducts, orderNumber);

        return "finish";
    }
}
