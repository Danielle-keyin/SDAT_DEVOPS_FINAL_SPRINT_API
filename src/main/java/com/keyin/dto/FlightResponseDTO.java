package com.keyin.aviation.dto;

import java.time.LocalDateTime;

public class FlightResponseDTO {

    private Long id;
    private String flightNumber;
    private String airline;
    private String type; // ARRIVAL / DEPARTURE
    private String origin;
    private String destination;
    private String gate;
    private String status;
    private LocalDateTime scheduledTime;
    private String airportCode;

    /* ===== Constructors ===== */

    public FlightResponseDTO() {}

    public FlightResponseDTO(
            Long id,
            String flightNumber,
            String airline,
            String type,
            String origin,
            String destination,
            String gate,
            String status,
            LocalDateTime scheduledTime,
            String airportCode
    ) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.gate = gate;
        this.status = status;
        this.scheduledTime = scheduledTime;
        this.airportCode = airportCode;
    }

    /* ===== Getters & Setters ===== */

    public Long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getType() {
        return type;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getGate() {
        return gate;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
}
