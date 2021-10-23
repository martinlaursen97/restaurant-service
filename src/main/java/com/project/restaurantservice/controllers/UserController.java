package com.project.restaurantservice.controllers;

import com.project.restaurantservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return "index";
    }

    //@RequestMapping("/login")
    //public String login(Model model) {
    //    model.addAttribute("user", new User());
    //    return "login";
    //}

    //@RequestMapping("/loginVerify")
    //public String loginVerify(User user, Model model) {
    //    if (userService.correctDetails(user)) {
    //        model.addAttribute("user", user);
    //        return "index";
    //    }
    //    return "login";
    //}
//
    //@PostMapping("/registerVerify")
    //public String registerVerify(User user) {
    //    if (!userService.usernameTaken(user)) {
    //        userService.addNewUser(user);
    //        return "success";
    //    }
    //    return "register";
    //}
//
    //@RequestMapping("/register")
    //public String register(Model model) {
    //    model.addAttribute("user", new User());
    //    return "register";
    //}
}
