package com.example.spoticloudbackend.repositories.implementations;

import com.example.spoticloudbackend.entities.User;
import com.example.spoticloudbackend.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(entityManager.createQuery("select u from User u where u.deleted = false", User.class).getResultList());
    }

    @Override
    public Set<User> findByUsername(String username) {
        return new HashSet<>(entityManager.createQuery("select u from User u where u.deleted = false and u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(entityManager.createQuery("select u from User u where u.deleted = false and u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult());
    }

    @Override
    public boolean existsByEmail(String email) {
        return !entityManager.createQuery("select u from User u where u.deleted = false and u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .isEmpty();
    }

}
