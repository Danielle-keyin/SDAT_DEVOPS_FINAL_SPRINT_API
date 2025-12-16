package com.keyin.aviation.service;

import com.keyin.aviation.exception.ResourceNotFoundException;
import com.keyin.aviation.model.Airport;
import com.keyin.aviation.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: " + id));
    }

    public Airport getAirportByCode(String code) {
        return airportRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with code: " + code));
    }

    public Airport createAirport(Airport airport) {
        if (airport.getCode() != null && airportRepository.existsByCodeIgnoreCase(airport.getCode())) {
            throw new IllegalArgumentException("Airport code already exists: " + airport.getCode());
        }
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport updated) {
        Airport existing = getAirportById(id);

        existing.setCode(updated.getCode());
        existing.setName(updated.getName());

        return airportRepository.save(existing);
    }

    public void deleteAirport(Long id) {
        Airport existing = getAirportById(id);
        airportRepository.delete(existing);
    }
}
