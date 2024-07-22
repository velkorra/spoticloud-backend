package com.example.spoticloudspringdata.repositories;

import com.example.spoticloudspringdata.entities.Release;
import com.example.spoticloudspringdata.entities.Track;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReleaseRepository{
    Release save(Release release);
    Optional<Release> findById(int id);
    List<Release> findAll();
    List<Track> findAllTracks(Release release);
}
