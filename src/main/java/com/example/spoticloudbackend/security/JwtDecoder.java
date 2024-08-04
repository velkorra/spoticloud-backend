package com.example.spoticloudbackend.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder implements org.springframework.security.oauth2.jwt.JwtDecoder {

    @Override
    public Jwt decode(String token) throws JwtException {
        return null;
    }
}
