package com.project.restaurantservice.repositories;


import com.project.restaurantservice.models.Order;
import com.project.restaurantservice.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.restaurantservice.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findUserByName(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findUserByName2(String username);

    @Query("SELECT u FROM User u where u.username = ?1 AND u.password = ?2")
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.userId = ?1")
    User findById2(Long id);
}
