package com.example.spoticloudbackend.repositories;

import com.example.spoticloudbackend.entities.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository {
    Genre save(Genre genre);

    Optional<Genre> findById(int id);

    List<Genre> findAll();

    Genre findByName(String name);

    List<Genre> findMainGenres();

}