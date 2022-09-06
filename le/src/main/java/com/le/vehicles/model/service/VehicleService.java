package com.le.vehicles.model.service;

import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.exceptions.VehiclesEntityNotFoundException;
import com.le.vehicles.model.repository.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private Vehicles vehiclesRepository;

    public List<Vehicle> getVehicles() {
        return vehiclesRepository.findAll();
    }

    public Vehicle findById(Long id) {
        try {
            return vehiclesRepository.findById(id).get();
        } catch (java.util.NoSuchElementException e) {
            throw new VehiclesEntityNotFoundException("ENTITY NOT FOUND >>>>>>>> " + e.getMessage());
        }
    }

    public Vehicle saveVehicles(Vehicle vehicle) {
        return vehiclesRepository.save(vehicle);
    }

    public List<Vehicle> findBType(String tipo) {
        return vehiclesRepository.findByTipo(tipo);
    }
}
