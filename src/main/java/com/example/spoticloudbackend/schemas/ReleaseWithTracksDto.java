package com.example.spoticloudbackend.schemas;

import com.example.spoticloudbackend.entities.Release;
import com.example.spoticloudbackend.entities.Track;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReleaseWithTracksDto extends ReleaseDto {

    private List<TrackDto> tracks;

    public ReleaseWithTracksDto(Release release) {
        super(release);
        this.tracks = release.getTracks().stream()
                .sorted(Comparator.comparingInt(Track::getAlbumPosition))
                .map(TrackDto::new)
                .collect(Collectors.toList());
    }

    public List<TrackDto> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDto> tracks) {
        this.tracks = tracks;
    }
}
