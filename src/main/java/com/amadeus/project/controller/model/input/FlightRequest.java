package com.amadeus.project.controller.model.input;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FlightRequest {

    @NotBlank
    private String departCity;

    private String arrivalCity;

    @NotBlank
    private OffsetDateTime departDate;

    private OffsetDateTime arrivalDate;

    @NotBlank
    private BigDecimal price;
}
