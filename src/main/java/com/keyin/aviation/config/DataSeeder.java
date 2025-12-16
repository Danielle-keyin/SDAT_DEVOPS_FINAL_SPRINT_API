package com.keyin.aviation.config;

import com.keyin.aviation.model.*;
import com.keyin.aviation.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(
            AirportRepository airportRepo,
            AirlineRepository airlineRepo,
            AircraftRepository aircraftRepo,
            GateRepository gateRepo,
            FlightRepository flightRepo
    ) {
        return args -> {
            if (airportRepo.count() > 0) return; // prevent reseeding every run

            // Airports
            Airport yyt = airportRepo.save(new Airport(null, "YYT", "St. John's (YYT)"));
            Airport yyz = airportRepo.save(new Airport(null, "YYZ", "Toronto Pearson (YYZ)"));
            Airport yul = airportRepo.save(new Airport(null, "YUL", "Montréal–Trudeau (YUL)"));

            // Airlines
            Airline ac = airlineRepo.save(new Airline(null, "AC", "Air Canada"));
            Airline ws = airlineRepo.save(new Airline(null, "WS", "WestJet"));

            // Aircraft
            Aircraft a320 = aircraftRepo.save(new Aircraft(null, "Airbus A320", "C-GA320"));
            Aircraft b737 = aircraftRepo.save(new Aircraft(null, "Boeing 737", "C-GB737"));

            // Gates (some per airport)
            Gate yytA3 = gateRepo.save(new Gate(null, "A3", yyt));
            Gate yytB1 = gateRepo.save(new Gate(null, "B1", yyt));
            Gate yyzC2 = gateRepo.save(new Gate(null, "C2", yyz));
            Gate yulD4 = gateRepo.save(new Gate(null, "D4", yul));

            // Flights
            Flight f1 = new Flight();
            f1.setFlightNumber("AC123");
            f1.setType(FlightType.ARRIVAL);
            f1.setOrigin("YYZ");
            f1.setDestination("YYT");
            f1.setScheduledTime(LocalDateTime.now().plusHours(2));
            f1.setStatus(FlightStatus.ON_TIME);
            f1.setAirport(yyt);
            f1.setAirline(ac);
            f1.setAircraft(a320);
            f1.setGate(yytA3);
            flightRepo.save(f1);

            Flight f2 = new Flight();
            f2.setFlightNumber("WS456");
            f2.setType(FlightType.DEPARTURE);
            f2.setOrigin("YYT");
            f2.setDestination("YUL");
            f2.setScheduledTime(LocalDateTime.now().plusHours(3));
            f2.setStatus(FlightStatus.BOARDING);
            f2.setAirport(yyt);
            f2.setAirline(ws);
            f2.setAircraft(b737);
            f2.setGate(yytB1);
            flightRepo.save(f2);

            Flight f3 = new Flight();
            f3.setFlightNumber("AC777");
            f3.setType(FlightType.ARRIVAL);
            f3.setOrigin("YUL");
            f3.setDestination("YYZ");
            f3.setScheduledTime(LocalDateTime.now().plusHours(1));
            f3.setStatus(FlightStatus.DELAYED);
            f3.setAirport(yyz);
            f3.setAirline(ac);
            f3.setAircraft(a320);
            f3.setGate(yyzC2);
            flightRepo.save(f3);

            Flight f4 = new Flight();
            f4.setFlightNumber("WS808");
            f4.setType(FlightType.DEPARTURE);
            f4.setOrigin("YUL");
            f4.setDestination("YYT");
            f4.setScheduledTime(LocalDateTime.now().plusHours(4));
            f4.setStatus(FlightStatus.ON_TIME);
            f4.setAirport(yul);
            f4.setAirline(ws);
            f4.setAircraft(b737);
            f4.setGate(yulD4);
            flightRepo.save(f4);
        };
    }
}
