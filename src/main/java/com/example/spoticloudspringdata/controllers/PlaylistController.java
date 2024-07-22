package com.example.spoticloudspringdata.controllers;

import com.example.spoticloudspringdata.entities.Playlist;
import com.example.spoticloudspringdata.schemas.PlaylistCreateDto;
import com.example.spoticloudspringdata.schemas.PlaylistDto;
import com.example.spoticloudspringdata.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/created/{userId}")
    public Set<PlaylistDto> getCreatedPlaylists(@PathVariable("userId") int userId) {
        return playlistService.ownPlaylists(userId);
    }

    @GetMapping
    public List<PlaylistDto> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @PostMapping("/{userId}/{playlistId}")
    public void addPlaylist(@PathVariable("userId") int userId, @PathVariable("playlistId") int playlistId) {
        playlistService.addPlaylist(userId, playlistId);
    }

    @PutMapping("make_public/{playlistId}")
    public void makePublic(@PathVariable int playlistId) {
        playlistService.makePublic(playlistId);
    }

    @PutMapping("make_private/{playlistId}")
    public void makePrivate(@PathVariable int playlistId) {
        playlistService.makePrivate(playlistId);
    }

    @PostMapping("share/{playlistId}")
    public String share(@PathVariable int playlistId) {
        return playlistService.sharePlaylist(playlistId);
    }

    @GetMapping("private/get/{token}")
    public PlaylistDto getPrivatePlaylist(@PathVariable String token) {
        return new PlaylistDto(playlistService.getPlaylistByToken(token));
    }

    @PostMapping
    public void createPlaylist(@RequestBody PlaylistCreateDto playlist) {
        playlistService.createPlaylist(playlist);
    }
}
