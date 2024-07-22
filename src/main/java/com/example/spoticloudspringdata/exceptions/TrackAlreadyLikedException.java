package com.example.spoticloudspringdata.exceptions;

public class TrackAlreadyLikedException extends RuntimeException {
    public TrackAlreadyLikedException(int id) {
        super("Track already liked with id " + id);
    }
}
