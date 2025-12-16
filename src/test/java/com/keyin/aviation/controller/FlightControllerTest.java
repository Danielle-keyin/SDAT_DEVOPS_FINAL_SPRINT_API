package com.keyin.aviation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.aviation.dto.FlightRequestDTO;
import com.keyin.aviation.dto.FlightResponseDTO;
import com.keyin.aviation.service.FlightService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private FlightService flightService;

    @Test
    void getAllFlights_returns200AndJsonArray() throws Exception {
        FlightResponseDTO dto = new FlightResponseDTO();
        dto.setId(1L);
        dto.setFlightNumber("AC123");
        dto.setType("ARRIVAL");
        dto.setOrigin("YYZ");
        dto.setDestination("YYT");
        dto.setScheduledTime(LocalDateTime.now());
        dto.setStatus("ON_TIME");

        Mockito.when(flightService.getAllFlights()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].flightNumber").value("AC123"));
    }

    @Test
    void createFlight_returns201AndCreatedFlight() throws Exception {
        FlightRequestDTO req = new FlightRequestDTO();
        req.setFlightNumber("WS200");
        req.setType("DEPARTURE");
        req.setOrigin("YYT");
        req.setDestination("YUL");
        req.setScheduledTime(LocalDateTime.now().plusHours(2));
        req.setStatus("ON_TIME");
        req.setAirportId(1L);
        req.setAirlineId(1L);
        req.setAircraftId(1L);
        req.setGateId(1L);

        FlightResponseDTO created = new FlightResponseDTO();
        created.setId(99L);
        created.setFlightNumber("WS200");

        Mockito.when(flightService.createFlight(any(FlightRequestDTO.class))).thenReturn(created);

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(99))
                .andExpect(jsonPath("$.flightNumber").value("WS200"));
    }
}
