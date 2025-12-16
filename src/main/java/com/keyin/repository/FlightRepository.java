package com.keyin.aviation.repository;

import com.keyin.aviation.model.Flight;
import com.keyin.aviation.model.FlightType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("""
        SELECT f
        FROM Flight f
        WHERE UPPER(f.airport.code) = UPPER(:airportCode)
          AND f.type = :type
        ORDER BY f.scheduledTime ASC
    """)
    List<Flight> findByAirportCodeAndType(
            @Param("airportCode") String airportCode,
            @Param("type") FlightType type
    );

    List<Flight> findByAirportId(Long airportId);
}
