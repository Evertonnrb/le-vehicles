package com.le.vehicles.model.repository;

import com.le.vehicles.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Vehicles extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByTipo(String tipo);
}
