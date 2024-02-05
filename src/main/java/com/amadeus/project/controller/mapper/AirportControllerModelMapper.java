package com.amadeus.project.controller.mapper;


import com.amadeus.project.controller.model.input.AirportRequest;
import com.amadeus.project.controller.model.output.AirportResponse;
import com.amadeus.project.domain.services.dto.request.AirportRequestDTO;
import com.amadeus.project.domain.services.dto.response.AirportResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportControllerModelMapper {

    List<AirportResponse> toAirportResponseList(List<AirportResponseDTO> airportResponseDTO);

    AirportRequestDTO toAirportRequestDTO(AirportRequest airportRequest);

    AirportResponse toAirportResponse(AirportResponseDTO airportResponseDTO);
}
