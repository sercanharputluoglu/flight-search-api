package com.amadeus.project.controller;


import com.amadeus.project.controller.mapper.FlightControllerModelMapper;
import com.amadeus.project.controller.model.input.FlightRequest;
import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.controller.model.output.FlightResponse;
import com.amadeus.project.domain.services.FlightSearchService;
import com.amadeus.project.domain.services.dto.request.FlightInfoRequestDTO;
import com.amadeus.project.domain.services.dto.request.FlightRequestDTO;
import com.amadeus.project.domain.services.dto.response.FlightInfoResponseDTO;
import com.amadeus.project.domain.services.dto.response.FlightResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/flights")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    private final FlightControllerModelMapper flightControllerMapper;

    @GetMapping
    public ResponseEntity<List<FlightInfoResponse>> getFlightInfo(FlightInfoRequestDTO flightInfoRequestDTO) {
        return ResponseEntity.ok(flightSearchService.getFlightInfo(flightInfoRequestDTO));
    }

    @GetMapping("/all-flights")
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        List<FlightInfoResponseDTO> flightInfoResponseDTOList = flightSearchService.getAllFlights();

        List<FlightResponse> flightInfoResponseList =
               flightControllerMapper.toFlightInfoResponseList(flightInfoResponseDTOList);

        return ResponseEntity.ok(flightInfoResponseList);
    }

    @PostMapping()
    public ResponseEntity<FlightResponse> addFlight(@RequestBody FlightRequest flightRequest){

        FlightRequestDTO flightRequestDTO = flightControllerMapper.toFlightRequestDTO(flightRequest);

        FlightResponseDTO flightResponseDTO = flightSearchService.addFlight(flightRequestDTO);

        FlightResponse flightResponse = flightControllerMapper.toFlightResponse(flightResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(flightResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable UUID id) {

        flightSearchService.deleteFlight(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight(
            @PathVariable UUID id, @RequestBody FlightRequest flightRequest) {

        FlightRequestDTO flightRequestDTO = flightControllerMapper.toFlightRequestDTO(flightRequest);

        FlightResponseDTO flightResponseDTO = flightSearchService.updateFlight(id, flightRequestDTO);

        FlightResponse flightResponse = flightControllerMapper.toFlightResponse(flightResponseDTO);

        return ResponseEntity.ok(flightResponse);
    }


}
