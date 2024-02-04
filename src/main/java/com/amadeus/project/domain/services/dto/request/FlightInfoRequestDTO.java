package com.amadeus.project.domain.services.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FlightInfoRequestDTO {

    private String departCity;
    private String arrivalCity;
    private OffsetDateTime departDate;
    private OffsetDateTime arrivalDate;
}
