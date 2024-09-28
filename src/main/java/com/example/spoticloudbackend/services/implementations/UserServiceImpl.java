package com.example.spoticloudbackend.services.implementations;

import com.example.spoticloudbackend.entities.LikedTracks;
import com.example.spoticloudbackend.entities.Track;
import com.example.spoticloudbackend.entities.User;
import com.example.spoticloudbackend.exceptions.*;
import com.example.spoticloudbackend.repositories.TrackRepository;
import com.example.spoticloudbackend.repositories.UserRepository;
import com.example.spoticloudbackend.schemas.*;
import com.example.spoticloudbackend.security.JwtService;
import com.example.spoticloudbackend.services.RecommendationService;
import com.example.spoticloudbackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TrackRepository trackRepository;
    private final RecommendationService recommendationService;
    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TrackRepository trackRepository, RecommendationService recommendationService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.trackRepository = trackRepository;
        this.recommendationService = recommendationService;
        this.jwtService = jwtService;

    }

    @Override
    public LoginResponse login(UserLogin userLogin) {
        User user = userRepository.findByUsername(userLogin.getUsername()).orElseThrow(
                () -> new UserNotFoundException(userLogin.getUsername())
        );
        if (!user.getPassword().equals(userLogin.getPassword())) {
            throw new InvalidPasswordException();
        }
        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }

    @Override
    public String validateToken(String token) {
        return jwtService.getUsernameFromToken(token);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }

    @Override
    @Transactional
    public Track listen(int userId, int trackId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        Track track = trackRepository.findById(trackId).orElseThrow(
                () -> new TrackNotFoundException(trackId)
        );
        user.addListeningHistory(track);
        recommendationService.adjustPreferences(user, track, 0.01f);
        user = userRepository.save(user);
        return recommendationService.recommendTrack(user);
    }

    @Override
    @Transactional
    public void likeTrack(int userId, int trackId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        Track track = trackRepository.findById(trackId).orElseThrow(
                () -> new TrackNotFoundException(trackId)
        );
        Set<Track> likedTracks = user.getLikedTracks().stream().map(LikedTracks::getTrack).collect(Collectors.toSet());
        if (likedTracks.contains(track)) {
            throw new TrackAlreadyLikedException(trackId);
        }
        user.addLikedTrack(track);
        recommendationService.adjustPreferences(user, track, 0.05f);
        userRepository.save(user);
    }

    @Override
    public List<TrackDto> getLikedTracks(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        return user.getLikedTracks().stream().map(LikedTracks::getTrack).map(TrackDto::new).toList();
    }

    @Override
    public UserDto getById(int id) {
        return userRepository.findById(id).map(UserDto::new).orElse(null);
    }

    @Override
    public UserDto getByUsername(String username) {
        return userRepository.findByUsername(username).map(UserDto::new).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    @Override
    public UserDto getByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDto::new).orElse(null);
    }


    @Override
    @Transactional
    public UserDto createUser(UserCreateDto user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
            return new UserDto(userRepository.save(newUser));
        }
        throw new EmailAlreadyRegistered(user.getEmail());
    }

    @Override
    public List<HistoryRecordDto> history(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        return user.getListenedTracks().stream().map(HistoryRecordDto::new).collect(Collectors.toList());
    }

    public void deleteUser(User user) {
        user.setDeleted(true);
        userRepository.save(user);
    }


    @Override
    @Transactional
    public void deleteUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        user.setDeleted(true);
        userRepository.save(user);
    }

}
