package com.example.spoticloudbackend.repositories;

import com.example.spoticloudbackend.entities.Playlist;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PlaylistRepository {
    Playlist save(Playlist playlist);

    Optional<Playlist> findById(int id);

    Set<Playlist> findAll();

    Set<Playlist> userCreatedPlaylists(int id);

}


