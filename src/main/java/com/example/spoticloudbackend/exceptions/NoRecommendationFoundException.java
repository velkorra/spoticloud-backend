package com.example.spoticloudbackend.exceptions;

public class NoRecommendationFoundException extends RuntimeException{
    public NoRecommendationFoundException(int userId){
        super("No recommendation found for user " + userId);
    }
}
