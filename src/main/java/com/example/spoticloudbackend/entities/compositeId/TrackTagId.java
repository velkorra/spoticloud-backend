package com.example.spoticloudbackend.entities.compositeId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TrackTagId implements Serializable {
    @Column(name = "track_id")
    private int trackId;
    @Column(name = "genre_id")
    private int genreId;

    protected TrackTagId() {}

    public TrackTagId(int trackId, int genreId) {
        this.trackId = trackId;
        this.genreId = genreId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackTagId trackTagId = (TrackTagId) o;
        return trackId == trackTagId.trackId && genreId == trackTagId.genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, genreId);
    }
}
