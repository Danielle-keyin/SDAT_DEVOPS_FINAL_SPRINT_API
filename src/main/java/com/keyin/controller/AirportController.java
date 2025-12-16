package com.keyin.aviation.controller;

import com.keyin.aviation.model.Airport;
import com.keyin.aviation.service.AirportService;
import com.keyin.aviation.dto.FlightResponseDTO;
import com.keyin.aviation.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;
    private final FlightService flightService;

    public AirportController(AirportService airportService, FlightService flightService) {
        this.airportService = airportService;
        this.flightService = flightService;
    }

    // CRUD - Airports
    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{code}")
    public Airport getAirportByCode(@PathVariable String code) {
        return airportService.getAirportByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return airportService.updateAirport(id, airport);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    // Arrivals/Departures (for frontend)
    @GetMapping("/{code}/arrivals")
    public List<FlightResponseDTO> getArrivals(@PathVariable String code) {
        return flightService.getArrivalsByAirportCode(code);
    }

    @GetMapping("/{code}/departures")
    public List<FlightResponseDTO> getDepartures(@PathVariable String code) {
        return flightService.getDeparturesByAirportCode(code);
    }
}
