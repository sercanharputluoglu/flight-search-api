package com.amadeus.project.controller;

import com.amadeus.project.controller.mapper.AirportControllerModelMapper;
import com.amadeus.project.controller.model.input.AirportRequest;
import com.amadeus.project.controller.model.output.AirportResponse;
import com.amadeus.project.domain.services.AirportService;
import com.amadeus.project.domain.services.dto.request.AirportRequestDTO;
import com.amadeus.project.domain.services.dto.response.AirportResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    private final AirportControllerModelMapper airportControllerModelMapper;

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAirports() {

        List<AirportResponseDTO> airportResponseDTO = airportService.getAirportList();

        List<AirportResponse> airportResponseList =
                airportControllerModelMapper.toAirportResponseList(airportResponseDTO);

        return ResponseEntity.ok(airportResponseList);
    }

    @PostMapping
    public ResponseEntity<AirportResponse> addAirport(@Valid @RequestBody AirportRequest airportRequest) {

        AirportRequestDTO airportRequestDTO = airportControllerModelMapper.toAirportRequestDTO(airportRequest);

        AirportResponseDTO airportResponseDTO = airportService.addAirport(airportRequestDTO);

        AirportResponse airportResponse = airportControllerModelMapper.toAirportResponse(airportResponseDTO);

       return ResponseEntity.status(HttpStatus.CREATED).body(airportResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable UUID id) {

        airportService.deleteAirport(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(
            @PathVariable UUID id, @Valid @RequestBody AirportRequest airportRequest) {

        AirportRequestDTO airportRequestDTO = airportControllerModelMapper.toAirportRequestDTO(airportRequest);

        AirportResponseDTO airportResponseDTO = airportService.updateAirport(id, airportRequestDTO);

        AirportResponse airportResponse = airportControllerModelMapper.toAirportResponse(airportResponseDTO);

        return ResponseEntity.ok(airportResponse);
    }


}
