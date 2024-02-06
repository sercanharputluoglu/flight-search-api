package com.amadeus.project.controller.mapper;


import com.amadeus.project.controller.model.input.FlightInfoRequest;
import com.amadeus.project.controller.model.input.FlightRequest;
import com.amadeus.project.controller.model.output.FlightResponse;
import com.amadeus.project.domain.services.dto.request.FlightInfoRequestDTO;
import com.amadeus.project.domain.services.dto.request.FlightRequestDTO;
import com.amadeus.project.domain.services.dto.response.FlightInfoResponseDTO;
import com.amadeus.project.domain.services.dto.response.FlightResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightControllerModelMapper {

    List<FlightResponse> toFlightInfoResponseList(List<FlightInfoResponseDTO> flightInfoResponseDTOList);

    FlightRequestDTO toFlightRequestDTO(FlightRequest flightRequest);

    FlightResponse toFlightResponse(FlightResponseDTO flightResponseDTO);

    FlightInfoRequestDTO toFlightInfoRequestDTO(FlightInfoRequest flightInfoRequest);
}
