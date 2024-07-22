package com.example.spoticloudspringdata.entities;

import com.example.spoticloudspringdata.entities.compositeId.ReleaseArtistId;
import jakarta.persistence.*;

@Entity
@Table(name = "release_artist")
public class ReleaseArtist {
    private ReleaseArtistId id;
    private Artist artist;
    private Release release;


    public ReleaseArtist(ReleaseArtistId id) {
        this.id = id;
    }

    protected ReleaseArtist() {

    }

    @EmbeddedId
    public ReleaseArtistId getId() {
        return id;
    }

    public void setId(ReleaseArtistId id) {
        this.id = id;
    }

    @ManyToOne
    @MapsId("artistId")
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @ManyToOne
    @MapsId("releaseId")
    @JoinColumn(name = "release_id", referencedColumnName = "id")
    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }
}

