package com.example.spoticloudbackend.services.implementations;


import com.example.spoticloudbackend.entities.Artist;
import com.example.spoticloudbackend.entities.Genre;
import com.example.spoticloudbackend.repositories.ArtistRepository;
import com.example.spoticloudbackend.repositories.GenreRepository;
import com.example.spoticloudbackend.schemas.ArtistDto;
import com.example.spoticloudbackend.services.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, GenreRepository genreRepository) {
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
    }

    public List<ArtistDto> getAllArtists() {
        return artistRepository.findAll().stream().map(ArtistDto::new).toList();
    }
    public Artist createArtist(ArtistDto artist) {
        Artist newArtist = new Artist(artist.getName(), artist.getType(), artist.getDescription(), artist.getCountry());
        Genre genre = genreRepository.findByName(artist.getGenre());
        newArtist.setGenre(genre);
        return artistRepository.save(newArtist);
    }
}
