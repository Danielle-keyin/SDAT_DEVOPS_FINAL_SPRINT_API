package com.keyin.aviation.dto;

import java.time.LocalDateTime;

public class FlightResponseDTO {

    private Long id;

    private Long airportId;
    private Long airlineId;
    private Long aircraftId;
    private Long gateId;

    private String flightNumber;
    private String airline;        // airline name (display)
    private String type;           // ARRIVAL / DEPARTURE
    private String origin;
    private String destination;
    private String gate;           // gate number (display)
    private String status;
    private LocalDateTime scheduledTime;
    private String airportCode;    // display (YYT, YUL, etc.)

    public FlightResponseDTO() {}

    public FlightResponseDTO(
            Long id,
            Long airportId,
            Long airlineId,
            Long aircraftId,
            Long gateId,
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
        this.airportId = airportId;
        this.airlineId = airlineId;
        this.aircraftId = aircraftId;
        this.gateId = gateId;
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


    public Long getId() { return id; }
    public Long getAirportId() { return airportId; }
    public Long getAirlineId() { return airlineId; }
    public Long getAircraftId() { return aircraftId; }
    public Long getGateId() { return gateId; }

    public String getFlightNumber() { return flightNumber; }
    public String getAirline() { return airline; }
    public String getType() { return type; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getGate() { return gate; }
    public String getStatus() { return status; }
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public String getAirportCode() { return airportCode; }

    public void setId(Long id) { this.id = id; }
    public void setAirportId(Long airportId) { this.airportId = airportId; }
    public void setAirlineId(Long airlineId) { this.airlineId = airlineId; }
    public void setAircraftId(Long aircraftId) { this.aircraftId = aircraftId; }
    public void setGateId(Long gateId) { this.gateId = gateId; }

    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public void setAirline(String airline) { this.airline = airline; }
    public void setType(String type) { this.type = type; }
    public void setOrigin(String origin) { this.origin = origin; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setGate(String gate) { this.gate = gate; }
    public void setStatus(String status) { this.status = status; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    public void setAirportCode(String airportCode) { this.airportCode = airportCode; }
}
