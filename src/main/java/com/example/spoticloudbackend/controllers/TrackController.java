package com.example.spoticloudbackend.controllers;

import com.example.spoticloudbackend.schemas.TrackDto;
import com.example.spoticloudbackend.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @GetMapping
    public List<TrackDto> getTracks() {
        return trackService.findAll();
    }
}
