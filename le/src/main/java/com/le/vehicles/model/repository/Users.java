package com.le.vehicles.model.repository;

import com.le.vehicles.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
