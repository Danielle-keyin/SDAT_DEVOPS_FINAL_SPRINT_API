package com.keyin.aviation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airports")
@JsonIgnoreProperties({"gates", "flights"})
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code; // YYT, YYZ, etc.

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gate> gates = new ArrayList<>();

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

    public Airport() {}

    public Airport(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public List<Gate> getGates() { return gates; }
    public List<Flight> getFlights() { return flights; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setGates(List<Gate> gates) { this.gates = gates; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
