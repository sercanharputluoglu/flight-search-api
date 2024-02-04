package com.amadeus.project.controller;


import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.domain.services.FlightSearchService;
import com.amadeus.project.domain.services.dto.request.FlightInfoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/flights")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @GetMapping
    public ResponseEntity<List<FlightInfoResponse>> getFlightInfo(FlightInfoRequestDTO flightInfoRequestDTO) {
        return ResponseEntity.ok(flightSearchService.getFlightInfo(flightInfoRequestDTO));
    }





}
