package com.example.spoticloudspringdata.services;

import com.example.spoticloudspringdata.entities.Release;
import com.example.spoticloudspringdata.entities.Track;
import com.example.spoticloudspringdata.exceptions.ReleaseNotFoundException;
import com.example.spoticloudspringdata.repositories.ReleaseRepository;
import com.example.spoticloudspringdata.schemas.ReleaseDto;
import com.example.spoticloudspringdata.schemas.ReleaseWithTracksDto;
import com.example.spoticloudspringdata.schemas.TrackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }
    public List<ReleaseDto> getAllReleases() {
        return releaseRepository.findAll().stream().map(ReleaseDto::new).toList();
    }

    public List<ReleaseWithTracksDto> getAllReleasesWithTracks() {
        return releaseRepository.findAll().stream().map(ReleaseWithTracksDto::new).toList();
    }
    public List<TrackDto> getAllTracks(Integer releaseId) {
        Release release = releaseRepository.findById(releaseId).orElseThrow(
                () -> new ReleaseNotFoundException(releaseId)
        );
        return releaseRepository.findAllTracks(release)
                .stream()
                .sorted(Comparator.comparingInt(Track::getAlbumPosition))
                .map(TrackDto::new).toList();
    }
}
