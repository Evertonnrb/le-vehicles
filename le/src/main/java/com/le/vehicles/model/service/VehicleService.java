package com.le.vehicles.model.service;

import com.le.vehicles.model.dto.VehicleDTO;
import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.repository.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private Vehicles vehiclesRepository;

    public List<VehicleDTO> getVehicles() {
        return vehiclesRepository.findAll().stream().map(VehicleDTO::create).collect(Collectors.toList());
    }

    public VehicleDTO findById(Long id) {
        return VehicleDTO.create(vehiclesRepository.findById(id).get());
    }

    public VehicleDTO saveVehicles(Vehicle vehicle) {
        return VehicleDTO.create(vehiclesRepository.save(vehicle));
    }

    public List<VehicleDTO> findBType(String tipo) {
        return vehiclesRepository.findByTipo(tipo).stream().map(VehicleDTO::create).collect(Collectors.toList());
    }

    public VehicleDTO updateVehicle(Vehicle vehicle, Long id) {
        Optional<Vehicle> vehicleOptional = vehiclesRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            Vehicle fromDb = vehicleOptional.get();
            fromDb.setNome(vehicle.getNome());
            fromDb.setTipo(vehicle.getTipo());
            return VehicleDTO.create(vehiclesRepository.save(fromDb));
        }
        return null;
    }

    public void deleteVehicle(Long id) {
        vehiclesRepository.deleteById(id);
    }
}
