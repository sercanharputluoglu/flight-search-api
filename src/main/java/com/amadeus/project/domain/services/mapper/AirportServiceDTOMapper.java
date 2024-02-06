package com.amadeus.project.domain.services.mapper;

import com.amadeus.project.domain.data.entity.AirportEntity;
import com.amadeus.project.domain.services.dto.request.AirportRequestDTO;
import com.amadeus.project.domain.services.dto.response.AirportResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportServiceDTOMapper {

    List<AirportResponseDTO> toAirportResponseDTOList(List<AirportEntity> airportEntityList);

    AirportEntity toAirportEntity(AirportRequestDTO airportRequestDTO);

    AirportResponseDTO toAirportResponseDTO(AirportEntity savedAirportEntity);

    AirportEntity updateAirportEntityFromDTO(
            @MappingTarget AirportEntity airportEntity, AirportRequestDTO airportRequestDTO);
}
