package com.keyin.aviation.repository;

import com.keyin.aviation.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCase(String code);
}
