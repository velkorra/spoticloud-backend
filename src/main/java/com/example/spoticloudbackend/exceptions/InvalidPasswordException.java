package com.example.spoticloudbackend.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("Invalid password");
    }
}
