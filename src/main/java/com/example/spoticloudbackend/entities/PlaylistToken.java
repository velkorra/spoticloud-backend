package com.example.spoticloudbackend.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "playlist_token")
public class PlaylistToken extends BaseEntity {
    private Playlist playlist;
    private String token;

    protected PlaylistToken() {
    }

    public PlaylistToken(Playlist playlist) {
        this.playlist = playlist;
        this.token = UUID.randomUUID().toString();
    }

    @ManyToOne
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Column(name = "token", updatable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
