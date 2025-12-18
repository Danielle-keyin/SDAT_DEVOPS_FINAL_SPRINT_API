package com.keyin.aviation.repository;

import com.keyin.aviation.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GateRepository extends JpaRepository<Gate, Long> {

    List<Gate> findByAirport_CodeIgnoreCase(String code);
}
