package com.example.spoticloudspringdata.entities;

import com.example.spoticloudspringdata.entities.compositeId.PlaylistAccessId;
import jakarta.persistence.*;

@Entity
@Table(name = "playlist_access")
public class PlaylistAccess {
    private PlaylistAccessId id;
    private Playlist playlist;
    private User user;

    public PlaylistAccess(Playlist playlist, User user){
        this.playlist = playlist;
        this.user = user;
        this.id = new PlaylistAccessId(user.getId(), playlist.getId());
    }

    protected PlaylistAccess() {

    }

    @EmbeddedId
    public PlaylistAccessId getId() {
        return id;
    }

    public void setId(PlaylistAccessId id) {
        this.id = id;
    }

    @ManyToOne
    @MapsId("playlistId")
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

