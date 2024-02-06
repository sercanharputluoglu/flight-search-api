package com.amadeus.project.controller.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportRequest {

    @NotBlank
    private String city;
}
