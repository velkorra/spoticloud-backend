package com.example.spoticloudbackend.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "track")
public class Track extends BaseEntity {
    private String name;
    private String language;
    private String type;
    private int duration;
    private Boolean explicit;
    private Genre genre;
    private Artist artist;
    private Release release;
    private Integer albumPosition;
    private Set<TrackTag> trackTags;


    public Track(String name, String language, String type, int duration, Boolean explicit, Genre genre, Artist artist, int albumPosition) {
        this.name = name;
        this.language = language;
        this.type = type;
        this.duration = duration;
        this.explicit = explicit;
        this.genre = genre;
        this.artist = artist;
        this.albumPosition = albumPosition;
    }

    protected Track() {

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

    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id", referencedColumnName = "id")
    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    @Basic
    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "explicit")
    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    @Column(name = "album_position")
    public Integer getAlbumPosition() {
        return albumPosition;
    }

    public void setAlbumPosition(Integer albumPosition) {
        this.albumPosition = albumPosition;
    }

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @OneToMany(mappedBy = "track")
    public Set<TrackTag> getTrackTags() {
        return trackTags;
    }

    @Transient
    public Set<Genre> getTags(){
        return getTrackTags().stream().map(TrackTag::getGenre).collect(Collectors.toSet());
    }

    public void setTrackTags(Set<TrackTag> trackTags) {
        this.trackTags = trackTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return duration == track.duration && Objects.equals(name, track.name) && Objects.equals(language, track.language) && Objects.equals(type, track.type) && Objects.equals(explicit, track.explicit) && Objects.equals(genre, track.genre) && Objects.equals(artist, track.artist) && Objects.equals(release, track.release) && Objects.equals(albumPosition, track.albumPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, language, type, duration, explicit, genre, artist, release, albumPosition);
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", duration=" + duration +
                ", explicit=" + explicit +
                ", genre=" + genre +
                '}';    }
}