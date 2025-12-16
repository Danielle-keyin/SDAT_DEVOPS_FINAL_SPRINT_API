package com.keyin.aviation.service;

import com.keyin.aviation.exception.ResourceNotFoundException;
import com.keyin.aviation.model.Airline;
import com.keyin.aviation.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(Long id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airline not found with id: " + id));
    }

    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline updateAirline(Long id, Airline updated) {
        Airline existing = getAirlineById(id);
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        return airlineRepository.save(existing);
    }

    public void deleteAirline(Long id) {
        Airline existing = getAirlineById(id);
        airlineRepository.delete(existing);
    }
}
