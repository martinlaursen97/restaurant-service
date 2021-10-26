package com.project.restaurantservice.controllers;


import com.project.restaurantservice.models.Courier;
import com.project.restaurantservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.restaurantservice.services.CourierService;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CourierController {

    private final CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @RequestMapping("/addCourier")
    public void addNewCourier() {
        Courier courier = new Courier("a", "b", "2635");
        courierService.addNewCourier(courier);
    }


}
