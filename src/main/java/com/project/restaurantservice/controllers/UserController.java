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

    @PostMapping("/loginVerify")
    public String loginVerify(WebRequest request)  {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("here");

        User user = userService.findUserByName(username);

        System.out.println(user);
        if (user == null) {
            return "login";
        }

        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);

        if (userService.correctDetails(username, password)) {
            return "redirect:/menu";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/registerVerify")
    public String registerVerify(WebRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");

        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(street);
        System.out.println(city);
        System.out.println(zip);
        System.out.println(phone);
        System.out.println(request);

        if (username == null ||
                password == null||
                email == null ||
                street == null ||
                city == null ||
                zip == null ||
                phone == null ) {

            return "register";
        }

        int min = 1;

        if (username.length() <= min ||
            password.length() <= min ||
            email.length() <= min    ||
            street.length() <= min   ||
            city.length() <= min     ||
            zip.length() <= min      ||
            phone.length() <= min) {

            return "register";
        }

        if (!userService.usernameTaken(username)) {
            userService.addNewUser(username, password, email, street, city, zip, phone);
            return "login";
        }
        return "redirect:/register";
    }
}
