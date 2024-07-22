package com.example.spoticloudbackend.entities.compositeId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReleaseArtistId implements Serializable {
    @Column(name = "artist_id")
    private int artistId;
    @Column(name = "release_id")
    private int releaseId;

    protected ReleaseArtistId() {
    }

    public ReleaseArtistId(int artistId, int releaseId) {
        this.artistId = artistId;
        this.releaseId = releaseId;
    }

    public int getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReleaseArtistId that = (ReleaseArtistId) o;
        return artistId == that.artistId && releaseId == that.releaseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, releaseId);
    }
}
