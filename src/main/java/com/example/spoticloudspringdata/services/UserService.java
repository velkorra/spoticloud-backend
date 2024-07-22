package com.example.spoticloudspringdata.services;

import com.example.spoticloudspringdata.entities.LikedTracks;
import com.example.spoticloudspringdata.entities.Track;
import com.example.spoticloudspringdata.entities.User;
import com.example.spoticloudspringdata.exceptions.EmailAlreadyRegistered;
import com.example.spoticloudspringdata.exceptions.TrackAlreadyLikedException;
import com.example.spoticloudspringdata.exceptions.TrackNotFoundException;
import com.example.spoticloudspringdata.exceptions.UserNotFoundException;
import com.example.spoticloudspringdata.repositories.TrackRepository;
import com.example.spoticloudspringdata.repositories.UserRepository;
import com.example.spoticloudspringdata.schemas.HistoryRecordDto;
import com.example.spoticloudspringdata.schemas.TrackDto;
import com.example.spoticloudspringdata.schemas.UserCreateDto;
import com.example.spoticloudspringdata.schemas.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TrackRepository trackRepository;
    private final RecommendationService recommendationService;

    @Autowired
    public UserService(UserRepository userRepository, TrackRepository trackRepository, RecommendationService recommendationService) {
        this.userRepository = userRepository;
        this.trackRepository = trackRepository;
        this.recommendationService = recommendationService;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }


    public Track listen(int userId, int trackId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        Track track = trackRepository.findById(trackId).orElseThrow(
                () -> new TrackNotFoundException(trackId)
        );
        user.addListeningHistory(track);
        recommendationService.adjustPreferences(user, track, 0.01f);
        userRepository.save(user);
        return recommendationService.recommendTrack(user);
    }

    @Transactional
    public void likeTrack(int userId, int trackId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        Track track = trackRepository.findById(trackId).orElseThrow(
                () -> new TrackNotFoundException(trackId)
        );
        Set<Track> likedTracks = user.getLikedTracks().stream().map(LikedTracks::getTrack).collect(Collectors.toSet());
        if (likedTracks.contains(track)){
            throw new TrackAlreadyLikedException(trackId);
        }
        user.addLikedTrack(track);
        recommendationService.adjustPreferences(user, track, 0.05f);
        userRepository.save(user);
    }

    public List<TrackDto> getLikedTracks(int userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        return user.getLikedTracks().stream().map(LikedTracks::getTrack).map(TrackDto::new).toList();
    }

    public UserDto getById(int id) {
        return userRepository.findById(id).map(UserDto::new).orElse(null);
    }

    public Set<UserDto> getByUsername(String username) {
        return userRepository.findByUsername(username).stream().map(UserDto::new).collect(Collectors.toSet());
    }


    public UserDto getByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDto::new).orElse(null);
    }


    public UserDto createUser(UserCreateDto user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
            return new UserDto(userRepository.save(newUser));
        }
        throw new EmailAlreadyRegistered(user.getEmail());
    }

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



    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        user.setDeleted(true);
        userRepository.save(user);
    }

}
