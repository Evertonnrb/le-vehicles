package com.le.vehicles.endpoint;

import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesEndPoint {
    @Autowired
    private VehicleService service;

    @GetMapping
    public List<Vehicle> getVehicles() {
        return service.getVehicles();
    }
}
