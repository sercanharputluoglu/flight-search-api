package com.amadeus.project.domain.services.mapper;

import com.amadeus.project.controller.FlightSearchController;
import com.amadeus.project.controller.model.output.FlightInfo;
import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.domain.data.entity.FlightEntity;
import com.amadeus.project.domain.services.dto.request.FlightRequestDTO;
import com.amadeus.project.domain.services.dto.response.FlightInfoResponseDTO;
import com.amadeus.project.domain.services.dto.response.FlightResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightInfoServiceDTOMapper {

//    default FlightInfoResponse toFlightInfoResponse(FlightEntity flightEntity) {
//
//        FlightInfo firstFlightInfo = new FlightInfo();
//        firstFlightInfo.setDepartCity(flightEntity.getDepartAirport().getCity());
//        firstFlightInfo.setArrivalCity(flightEntity.getArrivalAirport().getCity());
//        firstFlightInfo.setDepartDate(flightEntity.getDepartDate());
//
//        FlightInfoResponse flightInfoResponse = new FlightInfoResponse();
//        flightInfoResponse.setFlightInfos(List.of(firstFlightInfo));
//        flightInfoResponse.setPrice(flightEntity.getPrice());
//
//        return null;
//    };


    List<FlightInfoResponseDTO> toFlightInfoResponseDTOList(List<FlightEntity> flightEntityList);

    @Mapping(target = "departCity", source = "departAirport.city")
    @Mapping(target = "arrivalCity", source = "arrivalAirport.city")
    FlightInfoResponseDTO toFlightInfoResponseDTO(FlightEntity flightEntity);

    FlightEntity toFlightEntity(FlightRequestDTO flightRequestDTO);

    @Mapping(target = "departCity", source = "departAirport.city")
    @Mapping(target = "arrivalCity", source = "arrivalAirport.city")
    FlightResponseDTO toFlightResponseDTO(FlightEntity flightEntity);

    @Mapping(target = "departAirport.city", source = "departCity")
    @Mapping(target = "arrivalAirport.city", source = "arrivalCity")
    FlightEntity updateFlightEntityFromDTO(@MappingTarget FlightEntity flightEntity, FlightRequestDTO flightRequestDTO);
}
