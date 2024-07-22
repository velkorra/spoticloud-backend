package com.example.spoticloudbackend.exceptions;

public class ReleaseNotFoundException extends RuntimeException {
    public ReleaseNotFoundException(int id) {
        super("Release " + id + " not found");
    }
}
