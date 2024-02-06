package com.amadeus.project.domain.services.dto.request.authentication;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
