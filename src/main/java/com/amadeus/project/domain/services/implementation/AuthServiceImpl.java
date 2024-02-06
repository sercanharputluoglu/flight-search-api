package com.amadeus.project.domain.services.implementation;

import com.amadeus.project.domain.data.entity.UserEntity;
import com.amadeus.project.domain.services.AuthService;
import com.amadeus.project.domain.services.JwtService;
import com.amadeus.project.domain.services.UserService;
import com.amadeus.project.domain.services.dto.request.authentication.LoginRequest;
import com.amadeus.project.domain.services.dto.request.authentication.RegisterRequest;
import com.amadeus.project.domain.services.dto.response.authentication.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userService.saveUser(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Bad Credentials");
        }
        UserEntity user = userService.findUserByUsername(request.getUsername());

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }


}
