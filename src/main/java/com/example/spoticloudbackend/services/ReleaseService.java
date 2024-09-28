package com.example.spoticloudbackend.services;

import com.example.spoticloudbackend.schemas.ReleaseDto;
import com.example.spoticloudbackend.schemas.ReleaseWithTracksDto;
import com.example.spoticloudbackend.schemas.TrackDto;

import java.util.List;

public interface ReleaseService {
    List<ReleaseDto> getAllReleases();

    List<ReleaseWithTracksDto> getAllReleasesWithTracks();

    List<TrackDto> getAllTracks(Integer releaseId);
}
