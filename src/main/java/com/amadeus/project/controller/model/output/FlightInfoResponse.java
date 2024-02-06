package com.amadeus.project.controller.model.output;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class FlightInfoResponse {

    private BigDecimal price;
    private List<FlightInfo> flightInfos;
}
