package com.example.spoticloudbackend.entities;

import com.example.spoticloudbackend.entities.compositeId.UserPlaylistId;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_playlist")
public class UserPlaylist {
    private UserPlaylistId id;
    private Timestamp dateAdded;
    private User user;
    private Playlist playlist;


    public UserPlaylist(User user, Playlist playlist) {
        this.user = user;
        this.playlist = playlist;
        this.id = new UserPlaylistId(user.getId(), playlist.getId());
    }

    protected UserPlaylist() {

    }


    @EmbeddedId
    public UserPlaylistId getId() {
        return id;
    }
    public void setId(UserPlaylistId id) {
        this.id = id;
    }


    @Column(name = "date_added", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playlistId")
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlaylist that = (UserPlaylist) o;
        return Objects.equals(id, that.id) && Objects.equals(dateAdded, that.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAdded);
    }
}