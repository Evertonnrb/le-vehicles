package com.le.vehicles.endpoint;

import com.le.vehicles.model.dto.VehicleDTO;
import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehiclesEndPoint {
    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<List<?>> getVehicles() {
        return ResponseEntity.ok(service.getVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findVehiclesById(@PathVariable Long id) {
        Optional<VehicleDTO> vehicle = service.findById(id);

        return vehicle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{tipo}")
    public ResponseEntity<?> findByVehicleType(@PathVariable String tipo) {
        List<VehicleDTO> vehicleList = service.findBType(tipo);
        return vehicleList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(vehicleList);

    }

    @PostMapping
    public ResponseEntity<?> saveVehicles(@RequestBody Vehicle vehicle) {
        try {
            VehicleDTO v = service.saveVehicles(vehicle);
            URI location = getUri(v.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicles(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicle.setId(id);
        VehicleDTO dto = service.updateVehicle(vehicle, id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeVehicles(@PathVariable Long id) {
        boolean ok = service.deleteVehicle(id);
        return ok ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(id)
                .toUri();
    }
}
