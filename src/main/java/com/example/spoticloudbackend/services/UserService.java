package com.example.spoticloudbackend.services;

import com.example.spoticloudbackend.entities.Track;
import com.example.spoticloudbackend.entities.User;
import com.example.spoticloudbackend.schemas.*;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getById(int id);

    Track listen(int user, int track);

    List<TrackDto> getLikedTracks(int userId);

    void likeTrack(int userId, int trackId);

    UserDto getByEmail(String email);

    UserDto getByUsername(String username);

    UserDto createUser(UserCreateDto userDto);

    List<HistoryRecordDto> history(int userId);

    void deleteUser(User user);

    void deleteUserById(int id);

    LoginResponse login(UserLogin userLogin);

    String validateToken(String token);
}
