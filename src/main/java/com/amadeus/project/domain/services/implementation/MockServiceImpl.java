package com.amadeus.project.domain.services.implementation;

import com.amadeus.project.domain.data.entity.AirportEntity;
import com.amadeus.project.domain.data.entity.FlightEntity;
import com.amadeus.project.domain.data.repository.AirportRepository;
import com.amadeus.project.domain.data.repository.FlightRepository;
import com.amadeus.project.domain.services.MockService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MockServiceImpl implements MockService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final String[] airports = {"Londra", "Izmir", "Barcelona", "Paris", "Washington", "New York"};

    private List<FlightEntity> generateMockFlightData() {

        List<FlightEntity> flightEntityList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            FlightEntity flightEntity = new FlightEntity();

            flightEntity.setDepartAirport(getRandomAirport(0));
            flightEntity.setArrivalAirport(getRandomAirport(1));
            flightEntity.setDepartDate(generateRandomOffsetDateTime(0));
            flightEntity.setArrivalDate(generateRandomOffsetDateTime(1));
            flightEntity.setPrice(generateRandomPrice());

            flightEntityList.add(flightEntity);
        }

        return flightEntityList;
    }

    private OffsetDateTime generateRandomOffsetDateTime(int suffix) {

        OffsetDateTime now = OffsetDateTime.now();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        long daysToAdd = random.nextLong(1, 31);
        long hoursToAdd = random.nextLong(24);
        long minutesToAdd = random.nextLong(60);
        long secondsToAdd = random.nextLong(60);

        OffsetDateTime randomDate = now
                .plusDays(daysToAdd + suffix)
                .plusHours(hoursToAdd)
                .plusMinutes(minutesToAdd)
                .plusSeconds(secondsToAdd);

        return randomDate;
    }

    private BigDecimal generateRandomPrice() {

        Double price = ThreadLocalRandom.current().nextDouble(100, 8000);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedPrice = decimalFormat.format(price);

        return new BigDecimal(formattedPrice);
    }


    private AirportEntity getRandomAirport(int suffix) {

        int randomIndex = ThreadLocalRandom.current().nextInt(airports.length - 1);

        AirportEntity airportEntity = airportRepository
                .findByCity(airports[randomIndex + suffix])
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        return airportEntity;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void getMockFlightData() {
        List<FlightEntity> flightEntityList = generateMockFlightData();
        flightRepository.saveAll(flightEntityList);
    }
}
