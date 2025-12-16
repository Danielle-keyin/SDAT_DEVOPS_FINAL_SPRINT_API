package com.keyin.aviation.service;

import com.keyin.aviation.dto.FlightRequestDTO;
import com.keyin.aviation.model.*;
import com.keyin.aviation.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class FlightServiceTest {

    private FlightRepository flightRepository;
    private AirportRepository airportRepository;
    private AirlineRepository airlineRepository;
    private AircraftRepository aircraftRepository;
    private GateRepository gateRepository;

    private FlightService flightService;

    @BeforeEach
    void setup() {
        flightRepository = Mockito.mock(FlightRepository.class);
        airportRepository = Mockito.mock(AirportRepository.class);
        airlineRepository = Mockito.mock(AirlineRepository.class);
        aircraftRepository = Mockito.mock(AircraftRepository.class);
        gateRepository = Mockito.mock(GateRepository.class);

        flightService = new FlightService(
                flightRepository,
                airportRepository,
                airlineRepository,
                aircraftRepository,
                gateRepository
        );
    }

    @Test
    void createFlight_savesAndReturnsResponse() {
        // Arrange lookups
        Airport airport = new Airport(1L, "YYT", "St. John's (YYT)");
        Airline airline = new Airline(1L, "AC", "Air Canada");
        Aircraft aircraft = new Aircraft(1L, "A320", "C-TEST");
        Gate gate = new Gate(1L, "A3", airport);

        Mockito.when(airportRepository.findById(1L)).thenReturn(java.util.Optional.of(airport));
        Mockito.when(airlineRepository.findById(1L)).thenReturn(java.util.Optional.of(airline));
        Mockito.when(aircraftRepository.findById(1L)).thenReturn(java.util.Optional.of(aircraft));
        Mockito.when(gateRepository.findById(1L)).thenReturn(java.util.Optional.of(gate));

        Flight saved = new Flight();
        saved.setId(10L);
        saved.setFlightNumber("AC123");

        Mockito.when(flightRepository.save(any(Flight.class))).thenReturn(saved);

        FlightRequestDTO req = new FlightRequestDTO();
        req.setFlightNumber("AC123");
        req.setType("ARRIVAL");
        req.setOrigin("YYZ");
        req.setDestination("YYT");
        req.setScheduledTime(LocalDateTime.now());
        req.setStatus("ON_TIME");
        req.setAirportId(1L);
        req.setAirlineId(1L);
        req.setAircraftId(1L);
        req.setGateId(1L);

        var res = flightService.createFlight(req);

        assertNotNull(res);
        assertEquals("AC123", res.getFlightNumber());
    }
}
