package com.le.vehicles;

import com.le.vehicles.model.dto.VehicleDTO;
import com.le.vehicles.model.entities.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehiclesTestsApi {

    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<?> getVehicle(String url){
        return rest.getForEntity(url, VehicleDTO.class);
    }

    private ResponseEntity<List<?>> getVehicles(String url){
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<?>>() {
                });
    }

    @Test
    public void testSaveDelete(){
        Vehicle vehicle = new Vehicle();
        vehicle.setNome("Teste da aplicaçao");
        vehicle.setTipo("tipo de teste");

        //insert
        ResponseEntity response = rest.postForEntity("/api/v1/vehicles/", vehicle, null);
        System.out.println(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //find
        String location = response.getHeaders().get("location").get(0);
       // VehicleDTO dto = getVehicle(location).getBody();

        //delete
        rest.delete(location);
        assertEquals(HttpStatus.BAD_REQUEST, getVehicle(location).getStatusCode());
    }

    @Test
    public void testList(){
        List<?> vehicles = getVehicles("/api/v1/vehicles/").getBody();
        assertNotNull(vehicles);
    }

    @Test
    public void testListForType(){
        assertEquals(10, getVehicles("/api/v1/vehicles/tipo/classicos").getBody().size());
        assertEquals(10, getVehicles("/api/v1/vehicles/tipo/esportivos").getBody().size());
        assertEquals(10, getVehicles("/api/v1/vehicles/tipo/luxo").getBody().size());
        assertEquals(HttpStatus.NO_CONTENT, getVehicles("/api/v1/vehicles/tipo/xxx").getStatusCode());
    }
}
