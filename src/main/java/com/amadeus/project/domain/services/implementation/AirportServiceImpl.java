package com.amadeus.project.domain.services.implementation;

import com.amadeus.project.domain.data.entity.AirportEntity;
import com.amadeus.project.domain.data.repository.AirportRepository;
import com.amadeus.project.domain.services.AirportService;
import com.amadeus.project.domain.services.dto.request.AirportRequestDTO;
import com.amadeus.project.domain.services.dto.response.AirportResponseDTO;
import com.amadeus.project.domain.services.mapper.AirportServiceDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    private final AirportServiceDTOMapper airportServiceDTOMapper;

    @Override
    public List<AirportResponseDTO> getAirportList() {

        List<AirportEntity> airportEntityList = airportRepository.findAll();

        List<AirportResponseDTO> airportResponseDTO = airportServiceDTOMapper.toAirportResponseDTOList(airportEntityList);

        return airportResponseDTO;
    }

    @Override
    public AirportResponseDTO addAirport(AirportRequestDTO airportRequestDTO) {

        AirportEntity airportEntity = airportServiceDTOMapper.toAirportEntity(airportRequestDTO);

        AirportEntity savedAirportEntity = airportRepository.save(airportEntity);

        AirportResponseDTO airportResponseDTO = airportServiceDTOMapper.toAirportResponseDTO(savedAirportEntity);

        return airportResponseDTO;
    }

    @Override
    public void deleteAirport(UUID id) {

        AirportEntity airportEntity = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        airportRepository.delete(airportEntity);
    }

    @Override
    public AirportResponseDTO updateAirport(UUID id, AirportRequestDTO airportRequestDTO) {

        AirportEntity airportEntity = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        airportEntity = airportServiceDTOMapper.updateAirportEntityFromDTO(airportEntity, airportRequestDTO);

        airportEntity = airportRepository.save(airportEntity);

        AirportResponseDTO airportResponseDTO = airportServiceDTOMapper.toAirportResponseDTO(airportEntity);

        return airportResponseDTO;
    }
}
