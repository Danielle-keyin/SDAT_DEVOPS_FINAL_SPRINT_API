package com.keyin.aviation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "gates")
@JsonIgnoreProperties({"airport"})
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gateNumber; // A3, B1, etc.

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport airport;

    public Gate() {}

    public Gate(Long id, String gateNumber, Airport airport) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.airport = airport;
    }

    public Long getId() { return id; }
    public String getGateNumber() { return gateNumber; }
    public Airport getAirport() { return airport; }

    public void setId(Long id) { this.id = id; }
    public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }
    public void setAirport(Airport airport) { this.airport = airport; }
}
