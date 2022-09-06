package com.le.vehicles.endpoint;

import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehiclesEndPoint {
    @Autowired
    private VehicleService service;

    @GetMapping
    public List<Vehicle> getVehicles() {
        return service.getVehicles();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findVehiclesById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
    @GetMapping("/type/{tipo}")
    public ResponseEntity<?> findByVehicleType(@PathVariable String tipo){
        return ResponseEntity.ok().body(service.findBType(tipo));
    }

    @PostMapping
    public ResponseEntity<?> saveVehicles(@RequestBody Vehicle vehicle){
        return ResponseEntity.ok().body(service.saveVehicles(vehicle));
    }
}
