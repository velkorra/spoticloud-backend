package com.example.spoticloudbackend.repositories;


import com.example.spoticloudbackend.entities.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository{
    Artist save(Artist artist);
    Optional<Artist> findById(int id);
    List<Artist> findAll();
    List<Artist> findByName(String name);

}


