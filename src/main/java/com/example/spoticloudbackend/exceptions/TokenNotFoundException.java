package com.example.spoticloudbackend.exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String token) {
        super("Token not found: " + token);
    }
}
