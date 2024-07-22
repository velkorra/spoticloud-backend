package com.example.spoticloudbackend.repositories;

import com.example.spoticloudbackend.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository {
    User save(User user);

    Optional<User> findById(int id);

    Set<User> findAll();

    Set<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}

