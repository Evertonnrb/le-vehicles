package com.le.vehicles.endpoint;

import com.le.vehicles.model.dto.VehicleDTO;
import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    public ResponseEntity<VehicleDTO> findVehiclesById(@PathVariable Long id) {
        VehicleDTO vehicle = service.findById(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/type/{tipo}")
    public ResponseEntity<?> findByVehicleType(@PathVariable String tipo) {
        List<VehicleDTO> vehicleList = service.findBType(tipo);
        return vehicleList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(vehicleList);

    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> saveVehicles(@RequestBody Vehicle vehicle) {
        VehicleDTO v = service.saveVehicles(vehicle);
        URI location = getUri(v.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> updateVehicles(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicle.setId(id);
        VehicleDTO dto = service.updateVehicle(vehicle, id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> removeVehicles(@PathVariable Long id) {
        service.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(id)
                .toUri();
    }
}
