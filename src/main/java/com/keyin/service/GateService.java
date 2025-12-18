package com.keyin.aviation.service;

import com.keyin.aviation.exception.ResourceNotFoundException;
import com.keyin.aviation.model.Airport;
import com.keyin.aviation.model.Gate;
import com.keyin.aviation.repository.AirportRepository;
import com.keyin.aviation.repository.GateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    public GateService(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    public Gate getGateById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id: " + id));
    }

    public Gate createGate(Gate gate) {
        if (gate.getAirport() != null && gate.getAirport().getId() != null) {
            Long airportId = gate.getAirport().getId();

            Airport airport = airportRepository.findById(airportId)
                    .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: " + airportId));

            gate.setAirport(airport);
        }

        return gateRepository.save(gate);
    }

    public Gate updateGate(Long id, Gate updatedGate) {
        Gate existing = gateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id: " + id));

        existing.setGateNumber(updatedGate.getGateNumber());

        if (updatedGate.getAirport() != null && updatedGate.getAirport().getId() != null) {
            Long airportId = updatedGate.getAirport().getId();

            Airport airport = airportRepository.findById(airportId)
                    .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: " + airportId));

            existing.setAirport(airport);
        }

        return gateRepository.save(existing);
    }

    public void deleteGate(Long id) {
        Gate existing = gateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id: " + id));

        gateRepository.delete(existing);
    }

    public List<Gate> getGatesByAirportCode(String airportCode) {
        airportRepository.findByCodeIgnoreCase(airportCode)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with code: " + airportCode));

        return gateRepository.findByAirport_CodeIgnoreCase(airportCode);
    }
}
