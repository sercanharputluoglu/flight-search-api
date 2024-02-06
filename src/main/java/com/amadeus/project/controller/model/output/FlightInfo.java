package com.amadeus.project.controller.model.output;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class FlightInfo {
    private String departCity;
    private String arrivalCity;
    private OffsetDateTime departDate;
}
