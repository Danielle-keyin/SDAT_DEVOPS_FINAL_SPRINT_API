package com.keyin.aviation.service;

import com.keyin.aviation.exception.ResourceNotFoundException;
import com.keyin.aviation.model.Aircraft;
import com.keyin.aviation.repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aircraft not found with id: " + id));
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updated) {
        Aircraft existing = getAircraftById(id);
        existing.setModel(updated.getModel());
        existing.setRegistration(updated.getRegistration());
        return aircraftRepository.save(existing);
    }

    public void deleteAircraft(Long id) {
        Aircraft existing = getAircraftById(id);
        aircraftRepository.delete(existing);
    }
}
