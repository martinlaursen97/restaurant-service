package com.project.restaurantservice.controllers;

import com.project.restaurantservice.models.Order;
import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.models.User;
import com.project.restaurantservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.restaurantservice.services.OrderService;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    // hmm
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
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
                              @RequestParam(value="date") String date, Model model, WebRequest request) {

        model.addAttribute("orderNumber", orderNumber);
        model.addAttribute("date", date);
        request.removeAttribute("chosen", WebRequest.SCOPE_SESSION);

        return "finishOrder";
    }

    @RequestMapping("/orders")
    public String showOrders(WebRequest request, Model model) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Long roleId = user.getUserRole();

        List<Order> orders;
        orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        if (roleId == 1L) {
            return "ordersCustomer";
        } else {
            return "ordersAdmin";
        }
    }

    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public String orderSearch(WebRequest request, Model model) {
        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.length() == 0) {
            return "redirect:/orders";
        }

        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Long roleId = user.getUserRole();
        Order order = orderService.searchFor(keyword);
        model.addAttribute("orders", order);
        if (roleId == 1L) {
            return "ordersCustomer";
        } else {
            return "ordersAdmin";
        }
    }

    @RequestMapping(value = "/inspect", method = RequestMethod.GET)
    public String inspectOrder(@RequestParam(name="orderNumber") Long orderNumber, Model model, WebRequest request) {
        Order order = orderService.findByOrderNumber(orderNumber);
        model.addAttribute("order", order);

        List<Long> productsId = orderService.getOrderProducts(orderNumber);
        System.out.println(productsId.size());
        List<Product> products = productService.getProductsById(productsId);
        model.addAttribute("products", products);

        Double total = productService.getTotal(products);
        model.addAttribute("total", total);

        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Long roleId = user.getUserRole();

        if (roleId == 1L) {
            return "inspectOrderCustomer";
        }
        return "inspect";
    }
}
