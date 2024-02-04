package com.amadeus.project.domain.services.mapper;

import com.amadeus.project.controller.FlightSearchController;
import com.amadeus.project.controller.model.output.FlightInfo;
import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.domain.data.entity.FlightEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightInfoServiceDTOMapper {

    default FlightInfoResponse toFlightInfoResponse(FlightEntity flightEntity) {

        FlightInfo firstFlightInfo = new FlightInfo();
        firstFlightInfo.setDepartCity(flightEntity.getDepartAirport().getCity());
        firstFlightInfo.setArrivalCity(flightEntity.getArrivalAirport().getCity());
        firstFlightInfo.setDepartDate(flightEntity.getDepartDate());

        FlightInfoResponse flightInfoResponse = new FlightInfoResponse();
        flightInfoResponse.setFlightInfos(List.of(firstFlightInfo));
        flightInfoResponse.setPrice(flightEntity.getPrice());

        return null;
    };

}
