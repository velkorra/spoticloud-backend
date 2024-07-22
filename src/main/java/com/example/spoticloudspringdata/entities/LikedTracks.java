package com.example.spoticloudspringdata.entities;


import com.example.spoticloudspringdata.entities.compositeId.LikedTracksId;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "liked_tracks")
public class LikedTracks {
    private LikedTracksId id;
    private Track track;
    private User user;
    private Timestamp dateAdded;

    protected LikedTracks() {
    }

    public LikedTracks(User user, Track track){
        this.user = user;
        this.track = track;
        this.id = new LikedTracksId(track.getId(), user.getId());
    }

    @EmbeddedId
    public LikedTracksId getId() {
        return id;
    }

    public void setId(LikedTracksId id) {
        this.id = id;
    }

    @ManyToOne()
    @MapsId("trackId")
    @JoinColumn(name = "track_id", referencedColumnName = "id")
    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @ManyToOne()
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "date_added", insertable = false)
    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
}
