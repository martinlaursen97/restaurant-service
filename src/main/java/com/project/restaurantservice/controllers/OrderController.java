package com.project.restaurantservice.controllers;


import com.project.restaurantservice.models.Order;
import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.restaurantservice.services.OrderService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @RequestMapping("/finish")
    public RedirectView finish(WebRequest request, RedirectAttributes model) {
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

        model.addAttribute("orderNumber", orderNumber);
        model.addAttribute("date", dateStr);

        return new RedirectView("finishOrder");
    }

    @GetMapping("/finishOrder")
    public String finishOrder(@RequestParam(value="orderNumber") Long orderNumber,
                              @RequestParam(value="date") String date, Model model) {

        model.addAttribute("orderNumber", orderNumber);
        model.addAttribute("date", date);

        return "finishOrder";
    }

    @RequestMapping("/orders")
    public String showOrders(WebRequest request, Model model) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Long roleId = user.getUserRole();
        Long userId = user.getUserId();

        if (roleId == 1L) {
            List<Order> orders = orderService.getOrdersById(userId);
            model.addAttribute("orders", orders);
            return "ordersCustomer";
        } else {
            List<Order> orders = orderService.getAllOrders();
            model.addAttribute("orders", orders);
            return "ordersAdmin";
        }
    }

    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public String orderSearch(WebRequest request, Model model) {
        String keyword = request.getParameter("keyword");
        Order order = orderService.searchFor(keyword);
        model.addAttribute("products2", order);
        return "orderSearch";
    }
}
