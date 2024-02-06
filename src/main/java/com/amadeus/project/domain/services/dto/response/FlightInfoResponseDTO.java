package com.amadeus.project.domain.services.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class FlightInfoResponseDTO {

    private UUID id;
    private String departCity;
    private String arrivalCity;
    private OffsetDateTime departDate;
    private OffsetDateTime arrivalDate;
    private BigDecimal price;
}
