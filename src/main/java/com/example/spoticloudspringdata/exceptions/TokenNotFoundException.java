package com.example.spoticloudspringdata.exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String token) {
        super("Token not found: " + token);
    }
}
