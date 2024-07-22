package com.example.spoticloudbackend.services;


import com.example.spoticloudbackend.repositories.TrackRepository;
import com.example.spoticloudbackend.schemas.TrackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<TrackDto> findAll() {
        return trackRepository.findAll().stream().map(TrackDto::new).toList();
    }
}
