package com.example.spoticloudbackend.controllers;

import com.example.spoticloudbackend.entities.Artist;
import com.example.spoticloudbackend.schemas.ArtistDto;
import com.example.spoticloudbackend.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<ArtistDto> getAllArtists() {
        return artistService.getAllArtists();
    }

    @PostMapping
    public Artist addArtist(@RequestBody ArtistDto artist) {
        return artistService.createArtist(artist);
    }
}
