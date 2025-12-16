package com.keyin.aviation.controller;

import com.keyin.aviation.dto.FlightRequestDTO;
import com.keyin.aviation.dto.FlightResponseDTO;
import com.keyin.aviation.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // CRUD - Flights (Admin)
    @GetMapping
    public List<FlightResponseDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public FlightResponseDTO getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightResponseDTO createFlight(@RequestBody FlightRequestDTO request) {
        return flightService.createFlight(request);
    }

    @PutMapping("/{id}")
    public FlightResponseDTO updateFlight(@PathVariable Long id, @RequestBody FlightRequestDTO request) {
        return flightService.updateFlight(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
