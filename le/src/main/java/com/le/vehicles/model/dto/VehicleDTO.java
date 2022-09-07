package com.le.vehicles.model.dto;

import com.le.vehicles.model.entities.Vehicle;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class VehicleDTO {
    private Long id;
    private String nome;
    private String tipo;

    public static VehicleDTO create(Vehicle vehicle) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vehicle, VehicleDTO.class);
    }
}
