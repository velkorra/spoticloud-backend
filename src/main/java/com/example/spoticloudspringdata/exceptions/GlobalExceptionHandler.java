package com.example.spoticloudspringdata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NoRecommendationFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoRecommendationFound(NoRecommendationFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PlaylistNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePlaylistNotFound(PlaylistNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReleaseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleReleaseNotFound(ReleaseNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TrackAlreadyLikedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleTrackAlreadyLiked(TrackAlreadyLikedException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TrackNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTrackNotFound(TrackNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmailAlreadyRegistered.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserAlreadyExists(EmailAlreadyRegistered e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTokenNotFound(TokenNotFoundException e) {
        return e.getMessage();
    }

}