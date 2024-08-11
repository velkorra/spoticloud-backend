package com.example.spoticloudbackend.repositories.implementations;

import com.example.spoticloudbackend.entities.PlaylistToken;
import com.example.spoticloudbackend.repositories.PlaylistTokenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlaylistTokenRepositoryImpl implements PlaylistTokenRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public PlaylistTokenRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public PlaylistToken save(PlaylistToken playlistToken) {
        entityManager.persist(playlistToken);
        return playlistToken;
    }

    @Override
    public Optional<PlaylistToken> findByToken(String token) {
        return entityManager.createQuery("select pt from PlaylistToken pt where pt.token = :token", PlaylistToken.class)
                .setParameter("token", token)
                .getResultStream()
                .findFirst();
    }


}
