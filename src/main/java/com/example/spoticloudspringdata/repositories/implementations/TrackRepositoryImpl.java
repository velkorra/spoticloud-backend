package com.example.spoticloudspringdata.repositories.implementations;

import com.example.spoticloudspringdata.entities.Track;
import com.example.spoticloudspringdata.repositories.TrackRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrackRepositoryImpl implements TrackRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public TrackRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Track save(Track track) {
        entityManager.persist(track);
        return track;
    }

    @Override
    public Optional<Track> findById(int id) {
        return Optional.ofNullable(entityManager.find(Track.class, id));
    }

    @Override
    public List<Track> findAll() {
        return entityManager.createQuery("SELECT t FROM Track t", Track.class).getResultList();
    }
}
