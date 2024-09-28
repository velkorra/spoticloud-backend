package com.example.spoticloudbackend.services.implementations;


import com.example.spoticloudbackend.repositories.TrackRepository;
import com.example.spoticloudbackend.schemas.TrackDto;
import com.example.spoticloudbackend.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<TrackDto> findAll() {
        return trackRepository.findAll().stream().map(TrackDto::new).toList();
    }
}
