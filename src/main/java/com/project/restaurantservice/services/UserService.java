package com.project.restaurantservice.services;

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

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public boolean usernameTaken(User user) {
        Optional<User> userOptional = userRepository.findUserByName(user.getUsername());
        return userOptional.isPresent();
    }

    public boolean correctDetails(User user) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userOptional.isPresent();
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByName(String username) {
        return userRepository.findUserByName2(username);
    }
}

