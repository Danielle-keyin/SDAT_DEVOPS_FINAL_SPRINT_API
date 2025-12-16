package com.keyin.aviation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aircraft")
@JsonIgnoreProperties({"flights"})
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model; // e.g., Boeing 737, A320

    @Column(unique = true)
    private String registration; // optional (e.g., C-GXYZ)

    @OneToMany(mappedBy = "aircraft")
    private List<Flight> flights = new ArrayList<>();

    public Aircraft() {}

    public Aircraft(Long id, String model, String registration) {
        this.id = id;
        this.model = model;
        this.registration = registration;
    }

    public Long getId() { return id; }
    public String getModel() { return model; }
    public String getRegistration() { return registration; }
    public List<Flight> getFlights() { return flights; }

    public void setId(Long id) { this.id = id; }
    public void setModel(String model) { this.model = model; }
    public void setRegistration(String registration) { this.registration = registration; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
