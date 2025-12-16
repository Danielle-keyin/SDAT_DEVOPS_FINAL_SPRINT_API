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

    public List<Gate> getGatesByAirport(Long airportId) {
        return gateRepository.findByAirportId(airportId);
    }

    public Gate createGate(Gate gate) {
        if (gate.getAirport() == null || gate.getAirport().getId() == null) {
            throw new IllegalArgumentException("Gate must include an airport with an id");
        }

        Airport airport = airportRepository.findById(gate.getAirport().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: " + gate.getAirport().getId()));

        gate.setAirport(airport);
        return gateRepository.save(gate);
    }

    public Gate updateGate(Long id, Gate updated) {
        Gate existing = getGateById(id);

        existing.setGateNumber(updated.getGateNumber());

        if (updated.getAirport() != null && updated.getAirport().getId() != null) {
            Airport airport = airportRepository.findById(updated.getAirport().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: " + updated.getAirport().getId()));
            existing.setAirport(airport);
        }

        return gateRepository.save(existing);
    }

    public void deleteGate(Long id) {
        Gate existing = getGateById(id);
        gateRepository.delete(existing);
    }
}
