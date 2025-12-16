package com.keyin.aviation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@JsonIgnoreProperties({"airport", "airline", "aircraft", "gate"})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String flightNumber; // AC123

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightType type; // ARRIVAL / DEPARTURE

    @Column(nullable = false)
    private String origin; // YYZ

    @Column(nullable = false)
    private String destination; // YYT

    @Column(nullable = false)
    private LocalDateTime scheduledTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status; // ON_TIME, etc.

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport airport;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "gate_id", nullable = false)
    private Gate gate;

    public Flight() {}

    public Long getId() { return id; }
    public String getFlightNumber() { return flightNumber; }
    public FlightType getType() { return type; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public FlightStatus getStatus() { return status; }
    public Airport getAirport() { return airport; }
    public Airline getAirline() { return airline; }
    public Aircraft getAircraft() { return aircraft; }
    public Gate getGate() { return gate; }

    public void setId(Long id) { this.id = id; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public void setType(FlightType type) { this.type = type; }
    public void setOrigin(String origin) { this.origin = origin; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    public void setStatus(FlightStatus status) { this.status = status; }
    public void setAirport(Airport airport) { this.airport = airport; }
    public void setAirline(Airline airline) { this.airline = airline; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }
    public void setGate(Gate gate) { this.gate = gate; }
}
