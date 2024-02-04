package com.amadeus.project.domain.services.implementation;

import com.amadeus.project.controller.model.output.FlightInfo;
import com.amadeus.project.controller.model.output.FlightInfoResponse;
import com.amadeus.project.domain.data.entity.FlightEntity;
import com.amadeus.project.domain.data.repository.FlightRepository;
import com.amadeus.project.domain.services.FlightSearchService;
import com.amadeus.project.domain.services.dto.request.FlightInfoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {

    private final FlightRepository flightRepository;

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




//        List<FlightEntity> departFlightEntityList = flightRepository.findByDepartAirport_CityAndArrivalAirport_CityAndDepartDate(
//                flightInfoRequestDTO.getDepartCity(),
//                flightInfoRequestDTO.getArrivalCity(),
//                flightInfoRequestDTO.getDepartDate()
//        );
//
//        List<FlightEntity> returnFlightEntityList;
//
//        if (flightInfoRequestDTO.getArrivalDate() != null) {
//             returnFlightEntityList = flightRepository.findByDepartAirport_CityAndArrivalAirport_CityAndDepartDate(
//                    flightInfoRequestDTO.getArrivalCity(),
//                    flightInfoRequestDTO.getDepartCity(),
//                    flightInfoRequestDTO.getArrivalDate()
//            );
//        }






        return flightInfoResponseList;
    }





}
