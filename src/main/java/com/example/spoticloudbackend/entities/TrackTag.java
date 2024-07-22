package com.example.spoticloudbackend.entities;

import com.example.spoticloudbackend.entities.compositeId.TrackTagId;
import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class TrackTag {
    private TrackTagId Id;
    private Track track;
    private Genre genre;

    protected TrackTag(){}

    @EmbeddedId
    public TrackTagId getId() {
        return Id;
    }

    public void setId(TrackTagId id) {
        Id = id;
    }

    @ManyToOne
    @MapsId("trackId")
    @JoinColumn(name = "track_id", referencedColumnName = "id")
    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
