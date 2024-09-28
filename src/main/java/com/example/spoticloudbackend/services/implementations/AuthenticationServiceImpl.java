package com.example.spoticloudbackend.services.implementations;

import com.example.spoticloudbackend.repositories.UserRepository;

public class AuthenticationServiceImpl {
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
