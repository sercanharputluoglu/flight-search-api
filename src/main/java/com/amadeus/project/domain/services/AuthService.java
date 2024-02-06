package com.amadeus.project.domain.services;

import com.amadeus.project.domain.services.dto.request.authentication.LoginRequest;
import com.amadeus.project.domain.services.dto.request.authentication.RegisterRequest;
import com.amadeus.project.domain.services.dto.response.authentication.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(LoginRequest request);
}
