package com.example.spoticloudspringdata.repositories;

import com.example.spoticloudspringdata.entities.Track;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TrackRepository{
    Track save(Track track);
    Optional<Track> findById(int id);
    List<Track> findAll();
}
