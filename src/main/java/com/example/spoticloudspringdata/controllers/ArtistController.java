package com.example.spoticloudspringdata.controllers;

import com.example.spoticloudspringdata.entities.Artist;
import com.example.spoticloudspringdata.schemas.ArtistDto;
import com.example.spoticloudspringdata.services.ArtistService;
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
