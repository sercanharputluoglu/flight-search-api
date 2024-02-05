package com.amadeus.project.domain.data.repository;

import com.amadeus.project.domain.data.entity.AirportEntity;
import com.amadeus.project.domain.data.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, UUID> {
    List<FlightEntity> findByDepartAirport_CityAndArrivalAirport_CityAndDepartDate(
            String departCity, String arrivalCity, OffsetDateTime departDate);

    List<FlightEntity> findByDepartAirport_CityAndArrivalAirport_CityAndDepartDateAndArrivalDate(
            String departCity, String arrivalCity, OffsetDateTime departDate, OffsetDateTime arrivalDate);
}
