package com.project.restaurantservice.services;

import com.project.restaurantservice.models.Order;
import com.project.restaurantservice.models.User;
import com.project.restaurantservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean usernameTaken(String username) {
        Optional<User> userOptional = userRepository.findUserByName(username);
        return userOptional.isPresent();
    }

    public boolean correctDetails(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);
        return userOptional.isPresent();
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByName(String username) {
        return userRepository.findUserByName2(username);
    }

    public void addNewUser(String username,
                           String password,
                           String email,
                           String street,
                           String city,
                           String zip,
                           String phone) {
        User user = new User(username, password, email, street, city, zip, phone, 1L);
        userRepository.save(user);
    }

    public User findById(String id) {
        return userRepository.findById2(Long.parseLong(id));
    }
}

