package com.keyin.aviation.controller;

import com.keyin.aviation.model.Gate;
import com.keyin.aviation.service.GateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GateController {

    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @GetMapping("/gates")
    public List<Gate> getAllGates() {
        return gateService.getAllGates();
    }

    @GetMapping("/gates/{id}")
    public Gate getGateById(@PathVariable Long id) {
        return gateService.getGateById(id);
    }

    @GetMapping("/airports/{airportCode}/gates")
    public List<Gate> getGatesByAirportCode(@PathVariable String airportCode) {
        return gateService.getGatesByAirportCode(airportCode);
    }

    @PostMapping("/gates")
    @ResponseStatus(HttpStatus.CREATED)
    public Gate createGate(@RequestBody Gate gate) {
        return gateService.createGate(gate);
    }

    @PutMapping("/gates/{id}")
    public Gate updateGate(@PathVariable Long id, @RequestBody Gate gate) {
        return gateService.updateGate(id, gate);
    }

    @DeleteMapping("/gates/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGate(@PathVariable Long id) {
        gateService.deleteGate(id);
    }
}
