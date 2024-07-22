package com.example.spoticloudspringdata.repositories;


import com.example.spoticloudspringdata.entities.PlaylistToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistTokenRepository{
    PlaylistToken save(PlaylistToken playlistToken);
    Optional<PlaylistToken> findByToken(String token);

}


