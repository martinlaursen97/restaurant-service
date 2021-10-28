package com.project.restaurantservice.controllers;

import com.project.restaurantservice.models.Courier;
import com.project.restaurantservice.models.Product;
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

    @RequestMapping("/couriers")
    public String showCouriers(Model model) {
        model.addAttribute("couriers", courierService.fetchAllCouriers());
        return "couriers";
    }

    @RequestMapping(value = "/courierSearch", method = RequestMethod.GET)
    public String courierSearch(WebRequest request, Model model) {
        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.length() == 0) {
            return "redirect:/couriers";
        }

        Courier courier = courierService.findById(keyword);
        model.addAttribute("couriers", courier);
        return "couriers";
    }

    @RequestMapping(value = "/inspectCourier", method = RequestMethod.GET)
    public String inspectCourier(@RequestParam(name="courierId") String courierId, Model model) {
        Courier courier = courierService.findById(courierId);
        model.addAttribute("courier", courier);
        return "inspectCourier";
    }


    @RequestMapping("/addCourier")
    public String addNewCourier(Model model) {
        model.addAttribute("courier", new Courier());
        return "addCourier";
    }

    @PostMapping("/addC")
    public String addC(WebRequest request) {
        try {
            String zip = request.getParameter("zip");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");

            if (zip == null || firstname == null || lastname == null) {
                return "addCourier";
            }

            if (zip.length() == 0 || firstname.length() == 0 || lastname.length() == 0) {
                return "addCourier";
            }

            courierService.addNewCourier(zip, firstname, lastname);

            return "redirect:/couriers";
        } catch (NumberFormatException e) {
            return "addCourier";
        }
    }
}
