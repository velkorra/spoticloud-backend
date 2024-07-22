package com.example.spoticloudbackend.exceptions;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(int playlistId) {
        super("Playlist with id " + playlistId + " not found");
    }
}
