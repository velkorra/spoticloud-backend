package com.example.spoticloudspringdata.schemas;

import com.example.spoticloudspringdata.entities.Track;

import java.util.Set;
import java.util.stream.Collectors;

public class TrackDto {
    private int id;
    private String name;
    private String language;
    private String type;
    private String duration;
    private String explicit;
    private String genre;
    private String artist;
    private Set<GenreDto> tags;

    public TrackDto(Track track) {
        this.id = track.getId();
        this.name = track.getName();
        this.language = track.getLanguage();
        this.type = track.getType();
        this.artist = track.getArtist().getName();
        if (track.getExplicit()){
            this.explicit = "explicit";
        }
        else this.explicit = "not explicit";
        this.genre = track.getGenre().getName();
        int duration = track.getDuration();
        int minutes = duration / 60;
        int seconds = duration % 60;
        this.duration = String.format("%d:%02d", minutes, seconds);
        this.tags = track.getTags().stream().map(GenreDto::new).collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExplicit() {
        return explicit;
    }

    public void setExplicit(String explicit) {
        this.explicit = explicit;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<GenreDto> getTags() {
        return tags;
    }

    public void setTags(Set<GenreDto> tags) {
        this.tags = tags;
    }
}
