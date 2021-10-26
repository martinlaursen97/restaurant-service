package com.project.restaurantservice.services;

import com.project.restaurantservice.models.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.restaurantservice.repositories.CourierRepository;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    @Autowired
    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public void addNewCourier(Courier courier) {
        courierRepository.save(courier);
    }

}
