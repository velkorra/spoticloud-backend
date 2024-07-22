package com.example.spoticloudbackend.exceptions;

public class EmailAlreadyRegistered extends RuntimeException {
    public EmailAlreadyRegistered(String email){
        super("Email Already Registered");
    }
}
