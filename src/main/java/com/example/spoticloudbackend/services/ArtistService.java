package com.example.spoticloudbackend.services;


import com.example.spoticloudbackend.entities.Artist;
import com.example.spoticloudbackend.schemas.ArtistDto;

import java.util.List;

public interface ArtistService {
    List<ArtistDto> getAllArtists();

    Artist createArtist(ArtistDto artist);
}
