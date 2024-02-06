package com.amadeus.project.domain.services;

import com.amadeus.project.domain.data.entity.UserEntity;

import java.util.UUID;

public interface UserService {

    void saveUser(UserEntity newUser);


    UserEntity findUserByUsername(String username);
}
