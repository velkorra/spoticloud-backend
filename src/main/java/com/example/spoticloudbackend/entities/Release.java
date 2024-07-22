package com.example.spoticloudbackend.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Release extends BaseEntity {
    private String name;
    private Date dateReleased;
    private String language;
    private String type;
    private String description;
    private Genre genre;
    private Set<ReleaseArtist> releaseArtists;
    private Set<Track> tracks;


    protected Release() {

    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date_released")
    public Date getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(Date dateReleased) {
        this.dateReleased = dateReleased;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @OneToMany(mappedBy = "release")
    public Set<ReleaseArtist> getReleaseArtists() {
        return releaseArtists;
    }

    @OneToMany(mappedBy = "release")
    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }
    public void setReleaseArtists(Set<ReleaseArtist> releaseArtists) {
        this.releaseArtists = releaseArtists;
    }

    @Transient
    public Set<Artist> getArtists() {
        return getReleaseArtists().stream().map(ReleaseArtist::getArtist).collect(Collectors.toSet());
    }
}
