package com.amadeus.project.controller.model.output;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AirportResponse {

    private UUID id;
    private String city;
}
