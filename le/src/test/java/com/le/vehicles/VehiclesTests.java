package com.le.vehicles;

import com.le.vehicles.model.dto.VehicleDTO;
import com.le.vehicles.model.entities.Vehicle;
import com.le.vehicles.model.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class VehiclesTests {

    @Autowired
    private VehicleService service;

    @Test
    public void testVehicleInserFindDelete(){
        //insert
        Vehicle combi = new Vehicle();
        combi.setNome("Combi");
        combi.setTipo("Trabalho");
        VehicleDTO vehicleDTO = service.saveVehicles(combi);
        assertNotNull(vehicleDTO);

        Long id = vehicleDTO.getId();
        assertNotNull(id);

        //find
        VehicleDTO op = service.findById(id);
        assertTrue(op!=null);

        //delete
        service.deleteVehicle(op.getId());

    }
}
