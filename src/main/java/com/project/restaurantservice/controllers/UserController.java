package com.project.restaurantservice.controllers;

import com.project.restaurantservice.models.User;
import com.project.restaurantservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String login(WebRequest request)  {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findUserByName(username);
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        System.out.println(username);
        System.out.println(password);
        if (userService.correctDetails(username, password)) {
            return "menuCustomer";
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(WebRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");

        if (!userService.usernameTaken(username)) {
            userService.addNewUser(username, password, email, street, city, zip, phone);
            return "login";
        }
        return "register";
    }
}
