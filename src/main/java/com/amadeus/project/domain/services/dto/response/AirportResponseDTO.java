package com.amadeus.project.domain.services.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AirportResponseDTO {
    private UUID id;
    private String city;
}
