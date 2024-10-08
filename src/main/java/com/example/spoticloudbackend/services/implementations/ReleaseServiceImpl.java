package com.example.spoticloudbackend.services.implementations;

import com.example.spoticloudbackend.entities.Release;
import com.example.spoticloudbackend.entities.Track;
import com.example.spoticloudbackend.exceptions.ReleaseNotFoundException;
import com.example.spoticloudbackend.repositories.ReleaseRepository;
import com.example.spoticloudbackend.schemas.ReleaseDto;
import com.example.spoticloudbackend.schemas.ReleaseWithTracksDto;
import com.example.spoticloudbackend.schemas.TrackDto;
import com.example.spoticloudbackend.services.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
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
