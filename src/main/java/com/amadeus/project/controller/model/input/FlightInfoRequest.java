package com.amadeus.project.controller.model.input;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FlightInfoRequest {

    private String departCity;
    private String arrivalCity;
    private OffsetDateTime departDate;
    private OffsetDateTime arrivalDate;
    private BigDecimal price;
}
