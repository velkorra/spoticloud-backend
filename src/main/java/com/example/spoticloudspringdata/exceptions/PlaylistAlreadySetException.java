package com.example.spoticloudspringdata.exceptions;

public class PlaylistAlreadySetException extends RuntimeException {
    public PlaylistAlreadySetException(int playlistId, int userId) {
        super("Playlist with id " + playlistId + " already exists with id " + userId);
    }
}
