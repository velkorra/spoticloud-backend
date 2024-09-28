package com.example.spoticloudbackend.services.implementations;

import com.example.spoticloudbackend.entities.Genre;
import com.example.spoticloudbackend.repositories.GenreRepository;
import com.example.spoticloudbackend.schemas.GenreDto;
import com.example.spoticloudbackend.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream().map(GenreDto::new).toList();
    }
}
