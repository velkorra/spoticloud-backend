package com.example.spoticloudbackend.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlist")
public class Playlist extends BaseEntity {
    private String name;
    private boolean isPrivate;
    private Timestamp dateCreated;
    private String description;
    private User owner;
    private Set<TrackPlaylist> tracks = new HashSet<>();
    private Set<PlaylistToken> playlistTokens = new HashSet<>();

    public Playlist(String name, boolean isPrivate, String description, User owner) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.description = description;
        this.owner = owner;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    protected Playlist() {

    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_private", nullable = false)
    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Column(name = "date_created", insertable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    public Set<TrackPlaylist> getTracks() {
        return tracks;
    }

    @Transient
    public List<Track> getTrackList() {
        return tracks.stream().map(TrackPlaylist::getTrack).toList();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Transient
    public void addTrack(Track track){
        tracks.add(new TrackPlaylist(track, this));

    }

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    public Set<PlaylistToken> getPlaylistTokens() {
        return playlistTokens;
    }

    public void setPlaylistTokens(Set<PlaylistToken> playlistTokens) {
        this.playlistTokens = playlistTokens;
    }

    public void setTracks(Set<TrackPlaylist> tracks) {
        this.tracks = tracks;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}