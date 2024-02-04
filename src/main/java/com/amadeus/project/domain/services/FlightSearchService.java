package com.amadeus.project.domain.services;

import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.domain.services.dto.request.FlightInfoRequestDTO;

import java.util.List;

public interface FlightSearchService {

    List<FlightInfoResponse> getFlightInfo(FlightInfoRequestDTO flightInfoRequestDTO);
}
