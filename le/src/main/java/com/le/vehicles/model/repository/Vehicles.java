package com.le.vehicles.model.repository;

import com.le.vehicles.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Vehicles extends JpaRepository<Vehicle, Long> {
}
