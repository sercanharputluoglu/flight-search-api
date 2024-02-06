package com.amadeus.project.domain.services.implementation;

import com.amadeus.project.controller.model.output.FlightInfo;
import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.domain.data.entity.AirportEntity;
import com.amadeus.project.domain.data.entity.FlightEntity;
import com.amadeus.project.domain.data.repository.AirportRepository;
import com.amadeus.project.domain.data.repository.FlightRepository;
import com.amadeus.project.domain.services.FlightSearchService;
import com.amadeus.project.domain.services.dto.request.FlightInfoRequestDTO;
import com.amadeus.project.domain.services.dto.request.FlightRequestDTO;
import com.amadeus.project.domain.services.dto.response.FlightInfoResponseDTO;
import com.amadeus.project.domain.services.dto.response.FlightResponseDTO;
import com.amadeus.project.domain.services.mapper.FlightInfoServiceDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {

    private final FlightRepository flightRepository;

    private final FlightInfoServiceDTOMapper flightInfoServiceDTOMapper;

    private final AirportRepository airportRepository;

    @Override
    public List<FlightInfoResponse> getFlightInfo(FlightInfoRequestDTO flightInfoRequestDTO) {

        List<FlightEntity> flightEntityList = flightRepository
            .findByDepartAirport_CityAndArrivalAirport_CityAndDepartDateAndArrivalDate(
                flightInfoRequestDTO.getDepartCity(),
                flightInfoRequestDTO.getArrivalCity(),
                flightInfoRequestDTO.getDepartDate(),
                flightInfoRequestDTO.getArrivalDate()
        );

        List<FlightInfoResponse> flightInfoResponseList = flightEntityList.stream()
            .map(flightEntity -> {
                List<FlightInfo> flightInfoList = new ArrayList<>();

                FlightInfo firstFlightInfo = new FlightInfo();
                firstFlightInfo.setDepartCity(flightEntity.getDepartAirport().getCity());
                firstFlightInfo.setArrivalCity(flightEntity.getArrivalAirport().getCity());
                firstFlightInfo.setDepartDate(flightEntity.getDepartDate());

                flightInfoList.add(firstFlightInfo);

                if(flightEntity.getArrivalDate() != null) {
                    FlightInfo secondFlightInfo = new FlightInfo();
                    secondFlightInfo.setDepartCity(flightEntity.getArrivalAirport().getCity());
                    secondFlightInfo.setArrivalCity(flightEntity.getDepartAirport().getCity());
                    secondFlightInfo.setDepartDate(flightEntity.getArrivalDate());

                    flightInfoList.add(secondFlightInfo);
                }

                FlightInfoResponse flightInfoResponse = new FlightInfoResponse();
                flightInfoResponse.setPrice(flightEntity.getPrice());
                flightInfoResponse.setFlightInfos(flightInfoList);

                return flightInfoResponse;
            }).toList();

        return flightInfoResponseList;
    }

    @Override
    public List<FlightInfoResponseDTO> getAllFlights() {

        List<FlightEntity> flightEntityList = flightRepository.findAll();

        List<FlightInfoResponseDTO> flightInfoResponseDTOList =
                flightInfoServiceDTOMapper.toFlightInfoResponseDTOList(flightEntityList);

        return flightInfoResponseDTOList;
    }

    @Override
    public FlightResponseDTO addFlight(FlightRequestDTO flightRequestDTO) {

        FlightEntity flightEntity = flightInfoServiceDTOMapper.toFlightEntity(flightRequestDTO);

        AirportEntity departAirportEntity = airportRepository
                .findByCity(flightRequestDTO.getDepartCity())
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        AirportEntity arrivalAirportEntity = airportRepository
                .findByCity(flightRequestDTO.getArrivalCity()).orElse(null);

        flightEntity.setDepartAirport(departAirportEntity);
        flightEntity.setArrivalAirport(arrivalAirportEntity);

        flightEntity = flightRepository.save(flightEntity);

        FlightResponseDTO flightResponseDTO = flightInfoServiceDTOMapper.toFlightResponseDTO(flightEntity);

        return flightResponseDTO;
    }

    @Override
    public void deleteFlight(UUID id) {

        FlightEntity flightEntity = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        flightRepository.delete(flightEntity);
    }

    @Override
    public FlightResponseDTO updateFlight(UUID id, FlightRequestDTO flightRequestDTO) {

        FlightEntity flightEntity = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        flightEntity = flightInfoServiceDTOMapper.updateFlightEntityFromDTO(flightEntity, flightRequestDTO);

        flightEntity = flightRepository.save(flightEntity);

        FlightResponseDTO flightResponseDTO = flightInfoServiceDTOMapper.toFlightResponseDTO(flightEntity);

        return flightResponseDTO;
    }
}
