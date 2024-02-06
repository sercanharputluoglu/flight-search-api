package com.amadeus.project.domain.services;

import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.domain.services.dto.request.FlightInfoRequestDTO;
import com.amadeus.project.domain.services.dto.request.FlightRequestDTO;
import com.amadeus.project.domain.services.dto.response.FlightInfoResponseDTO;
import com.amadeus.project.domain.services.dto.response.FlightResponseDTO;

import java.util.List;
import java.util.UUID;

public interface FlightSearchService {

    List<FlightInfoResponse> getFlightInfo(FlightInfoRequestDTO flightInfoRequestDTO);

    List<FlightInfoResponseDTO> getAllFlights();

    FlightResponseDTO addFlight(FlightRequestDTO flightRequestDTO);

    void deleteFlight(UUID id);

    FlightResponseDTO updateFlight(UUID id, FlightRequestDTO flightRequestDTO);
}
