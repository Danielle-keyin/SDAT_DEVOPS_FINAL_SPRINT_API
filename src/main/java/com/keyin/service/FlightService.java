package com.keyin.aviation.service;

import com.keyin.aviation.dto.FlightRequestDTO;
import com.keyin.aviation.dto.FlightResponseDTO;
import com.keyin.aviation.exception.ResourceNotFoundException;
import com.keyin.aviation.model.*;
import com.keyin.aviation.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;
    private final GateRepository gateRepository;

    public FlightService(
            FlightRepository flightRepository,
            AirportRepository airportRepository,
            AirlineRepository airlineRepository,
            AircraftRepository aircraftRepository,
            GateRepository gateRepository
    ) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.aircraftRepository = aircraftRepository;
        this.gateRepository = gateRepository;
    }

    public List<FlightResponseDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FlightResponseDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
        return toResponseDTO(flight);
    }

    public FlightResponseDTO createFlight(FlightRequestDTO request) {
        Flight flight = new Flight();
        applyRequestToEntity(request, flight);
        return toResponseDTO(flightRepository.save(flight));
    }

    public FlightResponseDTO updateFlight(Long id, FlightRequestDTO request) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));

        applyRequestToEntity(request, flight);
        return toResponseDTO(flightRepository.save(flight));
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
        flightRepository.delete(flight);
    }

    public List<FlightResponseDTO> getArrivalsByAirportCode(String airportCode) {
        List<Flight> flights = flightRepository.findByAirportCodeAndType(
                airportCode,
                FlightType.ARRIVAL
        );
        return flights.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public List<FlightResponseDTO> getDeparturesByAirportCode(String airportCode) {
        List<Flight> flights = flightRepository.findByAirportCodeAndType(
                airportCode,
                FlightType.DEPARTURE
        );
        return flights.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }


    private FlightResponseDTO toResponseDTO(Flight flight) {
        return new FlightResponseDTO(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getAirline() != null ? flight.getAirline().getName() : null,
                flight.getType() != null ? flight.getType().name() : null,
                flight.getOrigin(),
                flight.getDestination(),
                flight.getGate() != null ? flight.getGate().getGateNumber() : null,
                flight.getStatus() != null ? flight.getStatus().name() : null,
                flight.getScheduledTime(),
                flight.getAirport() != null ? flight.getAirport().getCode() : null
        );
    }

    private void applyRequestToEntity(FlightRequestDTO request, Flight flight) {
        flight.setFlightNumber(request.getFlightNumber());
        flight.setOrigin(request.getOrigin());
        flight.setDestination(request.getDestination());
        flight.setScheduledTime(request.getScheduledTime());

        // Normalize enums safely
        flight.setType(parseFlightType(request.getType()));
        flight.setStatus(parseFlightStatus(request.getStatus()));

        Airport airport = airportRepository.findById(request.getAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: " + request.getAirportId()));
        Airline airline = airlineRepository.findById(request.getAirlineId())
                .orElseThrow(() -> new ResourceNotFoundException("Airline not found with id: " + request.getAirlineId()));
        Aircraft aircraft = aircraftRepository.findById(request.getAircraftId())
                .orElseThrow(() -> new ResourceNotFoundException("Aircraft not found with id: " + request.getAircraftId()));
        Gate gate = gateRepository.findById(request.getGateId())
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id: " + request.getGateId()));

        flight.setAirport(airport);
        flight.setAirline(airline);
        flight.setAircraft(aircraft);
        flight.setGate(gate);
    }

    private FlightType parseFlightType(String value) {
        if (value == null) throw new IllegalArgumentException("type is required");
        return FlightType.valueOf(value.trim().toUpperCase(Locale.ROOT));
    }

    private FlightStatus parseFlightStatus(String value) {
        if (value == null) throw new IllegalArgumentException("status is required");
        return FlightStatus.valueOf(value.trim().toUpperCase(Locale.ROOT));
    }
}
