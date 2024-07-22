package com.example.spoticloudspringdata.exceptions;

public class ReleaseNotFoundException extends RuntimeException {
    public ReleaseNotFoundException(int id) {
        super("Release " + id + " not found");
    }
}
