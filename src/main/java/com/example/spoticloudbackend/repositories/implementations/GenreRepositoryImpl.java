package com.example.spoticloudbackend.repositories.implementations;

import com.example.spoticloudbackend.entities.Genre;
import com.example.spoticloudbackend.repositories.GenreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public GenreRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Genre save(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    @Override
    public Optional<Genre> findById(int id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre findByName(String name) {
        return entityManager.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Genre> findMainGenres() {
        return entityManager.createQuery("SELECT g FROM Genre g WHERE g.parentGenre IS NULL", Genre.class).getResultList();
    }


}
