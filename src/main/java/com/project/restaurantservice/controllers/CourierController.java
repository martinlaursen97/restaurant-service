package com.project.restaurantservice.controllers;


import com.project.restaurantservice.models.Courier;
import com.project.restaurantservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.restaurantservice.services.CourierService;
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

    @RequestMapping("/couriers")
    public String showCouriers(Model model) {
        model.addAttribute("couriers", courierService.fetchAllCouriers());
        return "couriers";
    }

    @RequestMapping(value = "/courierSearch", method = RequestMethod.GET)
    public String courierSearch(WebRequest request, Model model) {
        String id = request.getParameter("id");
        Courier courier = courierService.findById(id);
        model.addAttribute("courier", courier);
        return "courierSearch";
    }

    @RequestMapping(value = "/inspectCourier", method = RequestMethod.GET)
    public String inspectCourier(@RequestParam(name="courierId") String courierId, Model model) {
        Courier courier = courierService.findById(courierId);
        model.addAttribute("courier", courier);
        return "inspectCourier";
    }
}
