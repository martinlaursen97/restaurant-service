package com.project.restaurantservice.controllers;

import com.project.restaurantservice.services.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderProductController {

    private final OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @RequestMapping("/addOrderProduct")
    public void addNewOrderProduct() {

    }

    @RequestMapping(value = "/orderProduct", method = RequestMethod.GET)
    public String orderProduct(@RequestParam(name="productId") Long productId) {
        //productService.removeProductById(productId);
        return "redirect:/menuAdmin";
    }
}
