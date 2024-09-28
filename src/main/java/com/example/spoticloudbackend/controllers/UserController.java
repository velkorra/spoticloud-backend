package com.example.spoticloudbackend.controllers;

import com.example.spoticloudbackend.exceptions.UnauthorizedException;
import com.example.spoticloudbackend.schemas.*;
import com.example.spoticloudbackend.services.PlaylistService;
import com.example.spoticloudbackend.services.RecommendationService;
import com.example.spoticloudbackend.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RecommendationService recommendationService;
    private final PlaylistService playlistService;

    public UserController(UserService userService, RecommendationService recommendationService, PlaylistService playlistService) {
        this.userService = userService;
        this.recommendationService = recommendationService;
        this.playlistService = playlistService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody UserLogin user) {
        return userService.login(user);
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaim("username");
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/listen/{user}/{track}")
    public TrackDto listen(@PathVariable("user") int user, @PathVariable("track") int track) {
        return new TrackDto(userService.listen(user, track));
    }

    @GetMapping("/history/{userId}")
    public List<HistoryRecordDto> getHistory(@PathVariable("userId") int userId) {
        return userService.history(userId);
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserCreateDto user) {
        return userService.createUser(user);
    }

    @GetMapping("like/{trackId}")
    public void like(@PathVariable("trackId") int trackId, @AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            throw new UnauthorizedException();
        }
        int userId = userService.getById(jwt.getClaim("id")).getId();
        userService.likeTrack(userId, trackId);
    }

    @GetMapping("liked")
    public List<TrackDto> getLikedTracks(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            throw new UnauthorizedException();
        }

        return userService.getLikedTracks(jwt.getClaim("id"));
    }

    @GetMapping("/recommend/{userID}")
    public TrackDto recommendTrack(@PathVariable int userID) {
        return new TrackDto(recommendationService.recommendTrack(userID));
    }

    @GetMapping("/recommended-tracks/{userId}")
    public List<TrackDto> getRecommendedTracks(@PathVariable int userId) {
        return recommendationService.recommendTracks(userId)
                .stream().map(TrackDto::new).toList();
    }

    @GetMapping("/recommended-playlist/{userId}")
    public PlaylistDto getRecommendedPlaylist(@PathVariable int userId) {
        return playlistService.createRecommendedPlaylist(userId);
    }

}
