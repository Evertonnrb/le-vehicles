package com.le.vehicles.model.service;

import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.repository.Vehicles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class VehicleService {
    @Autowired
    private Vehicles vehiclesRepository;

    public List<Vehicle> getVehicles() {
        return vehiclesRepository.findAll();
    }
}
