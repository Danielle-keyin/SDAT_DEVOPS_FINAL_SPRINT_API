package com.keyin.aviation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airlines")
@JsonIgnoreProperties({"flights"})
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code; // AC, WS, etc. (optional)

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights = new ArrayList<>();

    public Airline() {}

    public Airline(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public List<Flight> getFlights() { return flights; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
