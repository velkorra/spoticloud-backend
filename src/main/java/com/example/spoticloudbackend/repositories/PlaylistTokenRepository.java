package com.example.spoticloudbackend.repositories;


import com.example.spoticloudbackend.entities.PlaylistToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistTokenRepository{
    PlaylistToken save(PlaylistToken playlistToken);
    Optional<PlaylistToken> findByToken(String token);

}


