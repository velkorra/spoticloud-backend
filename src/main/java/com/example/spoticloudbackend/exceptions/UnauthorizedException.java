package com.example.spoticloudbackend.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(){
        super("Unauthorized");
    }
}
