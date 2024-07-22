package com.example.spoticloudbackend.schemas;

import com.example.spoticloudbackend.entities.Playlist;

import java.sql.Timestamp;
import java.util.List;

public class PlaylistDto {
    private int id;
    private String name;
    private boolean isPrivate;
    private Timestamp dateCreated;
    private String description;
    private String owner;
    private List<TrackDto> tracks;

    public PlaylistDto(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.isPrivate = playlist.isPrivate();
        this.dateCreated = playlist.getDateCreated();
        this.description = playlist.getDescription();
        this.owner = playlist.getOwner().getUsername();
        this.tracks = playlist.getTrackList().stream().map(TrackDto::new).toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<TrackDto> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDto> tracks) {
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
