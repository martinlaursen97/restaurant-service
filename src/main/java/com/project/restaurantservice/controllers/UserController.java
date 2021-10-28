package com.project.restaurantservice.controllers;

import com.project.restaurantservice.models.User;
import com.project.restaurantservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


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

        User user = userService.findUserByName(username);

        if (user == null) {
            return "login";
        }

        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);

        if (userService.correctDetails(username, password)) {
            return "redirect:/menu";
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(WebRequest request) {
        request.removeAttribute("user", WebRequest.SCOPE_SESSION);
        request.removeAttribute("chosen", WebRequest.SCOPE_SESSION);
        request.removeAttribute("choice", WebRequest.SCOPE_SESSION);

        return "redirect:/";
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

    @RequestMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.fetchAllUsers());
        return "users";
    }

    @RequestMapping(value = "/userSearch", method = RequestMethod.GET)
    public String userSearch(WebRequest request, Model model) {
        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.length() == 0) {
            return "redirect:/users";
        }

        User user = userService.findById(keyword);
        model.addAttribute("users", user);
        return "users";
    }

    @RequestMapping(value = "/inspectUser", method = RequestMethod.GET)
    public String inspectUser(@RequestParam(name="userId") String userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "inspectUser";
    }
}
