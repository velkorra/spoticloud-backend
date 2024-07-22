package com.example.spoticloudbackend.exceptions;

public class PlaylistAlreadySetException extends RuntimeException {
    public PlaylistAlreadySetException(int playlistId, int userId) {
        super("Playlist with id " + playlistId + " already exists with id " + userId);
    }
}
