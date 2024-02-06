package com.amadeus.project.domain.services.implementation;

import com.amadeus.project.domain.data.entity.UserEntity;
import com.amadeus.project.domain.data.repository.UserRepository;
import com.amadeus.project.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(UserEntity newUser) {
        userRepository.save(newUser);
    }


    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
