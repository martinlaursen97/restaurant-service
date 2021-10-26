package com.project.restaurantservice.repositories;

import com.project.restaurantservice.models.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

}
