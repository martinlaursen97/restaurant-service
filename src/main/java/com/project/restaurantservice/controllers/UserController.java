package com.project.restaurantservice.controllers;

import com.project.restaurantservice.models.User;
import com.project.restaurantservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/loginVerify")
    public String loginVerify(User user, Model model) {
        if (userService.correctDetails(user)) {
            user = userService.findUserByName(user.getUsername());
            model.addAttribute("user", user);
            if (user.getUserRole() == 1) {
                return "redirect:/menuCustomer";
            } else {
                return "redirect:/menuAdmin";
            }
        }
        return "login";
    }
//
    @PostMapping("/registerVerify")
    public String registerVerify(User user) {
        if (!userService.usernameTaken(user)) {
            userService.addNewUser(user);
            return "registerSuccess";
        }
        return "register";
    }
//
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
