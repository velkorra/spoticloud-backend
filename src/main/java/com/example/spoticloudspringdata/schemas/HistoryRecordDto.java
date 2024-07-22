package com.example.spoticloudspringdata.schemas;

import com.example.spoticloudspringdata.entities.Track;

public class HistoryRecordDto {
    private int id;
    private String name;
    private String artist;
    private String album;

    public HistoryRecordDto(Track track) {
        this.id = track.getId();
        this.name = track.getName();
        this.artist = track.getArtist().getName();
        this.album = track.getRelease().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
