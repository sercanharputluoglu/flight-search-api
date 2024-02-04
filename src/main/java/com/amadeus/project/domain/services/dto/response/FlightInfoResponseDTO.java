package com.amadeus.project.domain.services.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FlightInfoResponseDTO {

    private String departCity;
    private String arrivalCity;
    private OffsetDateTime departDate;
    private OffsetDateTime arrivalDate;
    private BigDecimal price;
}
