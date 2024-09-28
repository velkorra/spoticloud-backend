package com.example.spoticloudbackend.services;

import com.example.spoticloudbackend.schemas.TrackDto;

import java.util.List;

public interface TrackService {
    List<TrackDto> findAll();
}
