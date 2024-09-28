package com.example.spoticloudbackend.services;

import com.example.spoticloudbackend.entities.*;

import java.util.List;

public interface RecommendationService {
    void adjustPreferences(User user, Track track, float value);

    Track recommendTrack(int userId);

    Track recommendTrack(User user);

    Playlist recommendPlaylist(int userId);

    List<Track> recommendTracks(int userId);

    List<Track> recommendTracks(User user);
}
