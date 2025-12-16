package com.keyin.aviation.controller;

import com.keyin.aviation.model.Airline;
import com.keyin.aviation.service.AirlineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);
    }

    @PutMapping("/{id}")
    public Airline updateAirline(@PathVariable Long id, @RequestBody Airline airline) {
        return airlineService.updateAirline(id, airline);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
    }
}
