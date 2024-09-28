package com.example.spoticloudbackend.services;


import com.example.spoticloudbackend.entities.Genre;
import com.example.spoticloudbackend.schemas.GenreDto;

import java.util.List;

public interface GenreService {
    Genre findByName(String name);

    List<GenreDto> getAllGenres();
}
