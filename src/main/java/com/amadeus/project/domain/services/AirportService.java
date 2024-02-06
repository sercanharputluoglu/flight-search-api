package com.amadeus.project.domain.services;

import com.amadeus.project.domain.services.dto.request.AirportRequestDTO;
import com.amadeus.project.domain.services.dto.response.AirportResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AirportService {

    List<AirportResponseDTO> getAirportList();

    AirportResponseDTO addAirport(AirportRequestDTO airportRequestDTO);

    void deleteAirport(UUID id);

    AirportResponseDTO updateAirport(UUID id, AirportRequestDTO airportRequestDTO);
}
